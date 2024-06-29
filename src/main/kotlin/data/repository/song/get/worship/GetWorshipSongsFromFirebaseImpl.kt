package data.repository.song.get.worship

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import domain.model.Song
import domain.repository.song.get.worship.GetWorshipSongsFromFirebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GetWorshipSongsFromFirebaseImpl : GetWorshipSongsFromFirebase {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("Song")
    override suspend fun getWorshipSongsFromFirebase(): List<Song> = suspendCancellableCoroutine { continuation ->
        val worshipSongs = mutableListOf<Song>()
        val vListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (worshipSongs.size > 0) worshipSongs.clear()
                for (item: DataSnapshot in p0.children) {
                    val song: HashMap<Any, Any> = item.value as HashMap<Any, Any>
                    val isGlorifyingSong = song.getValue("glorifyingSong") as Boolean
                    val isWorshipSong = song.getValue("worshipSong") as Boolean
                    val isGiftSong = song.getValue("giftSong") as Boolean
                    val isFromSongbookSong = song.getValue("fromSongbookSong") as Boolean

                    if (isGlorifyingSong || isWorshipSong || isGiftSong || isFromSongbookSong) {
                        val title = song.getValue("title") as String
                        val tonality = song.getValue("tonality") as String
                        val words = song.getValue("words") as String
                        val temp = song.getValue("temp") as Int
                        val id = item.key as String

                        val songObj = Song(
                            id = id,
                            title = title,
                            tonality = tonality,
                            words = words,
                            temp = temp,
                            isGlorifyingSong = isGlorifyingSong,
                            isWorshipSong = isWorshipSong,
                            isGiftSong = isGiftSong,
                            isFromSongbookSong = isFromSongbookSong
                        )
                        if (songObj.isGlorifyingSong)
                            worshipSongs.add(songObj)
                    }
                }
                continuation.resume(worshipSongs)
            }

            override fun onCancelled(p0: DatabaseError) {
                continuation.resumeWithException(p0.toException())
            }
        }

        databaseRef.addListenerForSingleValueEvent(vListener)

        continuation.invokeOnCancellation {
            databaseRef.removeEventListener(vListener)
        }
    }
}