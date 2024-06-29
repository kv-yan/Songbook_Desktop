package data.repository.song.get.all

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import domain.model.Song
import domain.repository.song.get.all.GetSongsFromFirebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GetSongsFromFirebaseImpl : GetSongsFromFirebase {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("Song")

    override suspend fun getSongs(): List<Song> = suspendCancellableCoroutine { continuation ->
        val allSongList = mutableListOf<Song>()
        val vListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (allSongList.size > 0) allSongList.clear()
                for (item: DataSnapshot in p0.children) {
                    val song: HashMap<Any, Any> = item.value as HashMap<Any, Any>
                    val isGlorifyingSong = song.getValue("glorifyingSong") as Boolean
                    val isWorshipSong = song.getValue("worshipSong") as Boolean
                    val isGiftSong = song.getValue("giftSong") as Boolean
                    val isFromSongbookSong = song.getValue("fromSongbookSong") as Boolean

                    val title = song.getValue("title") as String
                    val tonality = song.getValue("tonality") as String
                    val words = song.getValue("words") as String
                    val temp = song.getValue("temp") as Long
                    val id = item.key as String

                    val songObj = Song(
                        id = id,
                        title = title,
                        tonality = tonality,
                        words = words,
                        temp = temp.toInt(),
                        isGlorifyingSong = isGlorifyingSong,
                        isWorshipSong = isWorshipSong,
                        isGiftSong = isGiftSong,
                        isFromSongbookSong = isFromSongbookSong
                    )
                    allSongList.add(songObj)
                }

                continuation.resume(allSongList)
            }

            override fun onCancelled(p0: DatabaseError) {
                continuation.resumeWithException(p0.toException())
            }
        }

        databaseRef.addListenerForSingleValueEvent(vListener)

        continuation.invokeOnCancellation {
            print("error")
            databaseRef.removeEventListener(vListener)
        }
    }
}