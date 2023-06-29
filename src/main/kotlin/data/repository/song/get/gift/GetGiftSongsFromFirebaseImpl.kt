package data.repository.song.get.gift

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import domain.model.Song
import domain.repository.song.get.gift.GetGiftSongsFromFirebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@Suppress("UNCHECKED_CAST")
class GetGiftSongsFromFirebaseImpl : GetGiftSongsFromFirebase {

    private val databaseRef = FirebaseDatabase.getInstance().getReference("Song")
    override suspend fun getGiftSongsFromFirebase(): List<Song> = suspendCancellableCoroutine { continuation ->
        val giftSongs = mutableListOf<Song>()
        val vListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (giftSongs.size > 0) giftSongs.clear()
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
                        val id = item.key as String

                        val songObj = Song(
                            id = id,
                            title = title,
                            tonality = tonality,
                            words = words,
                            isGlorifyingSong = isGlorifyingSong,
                            isWorshipSong = isWorshipSong,
                            isGiftSong = isGiftSong,
                            isFromSongbookSong = isFromSongbookSong
                        )
                        if (songObj.isGlorifyingSong)
                            giftSongs.add(songObj)
                    }
                }

                continuation.resume(giftSongs)
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