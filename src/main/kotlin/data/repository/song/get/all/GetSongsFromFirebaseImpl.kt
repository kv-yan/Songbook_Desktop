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
            override fun onDataChange(snapshot: DataSnapshot) {
                allSongList.clear()
                for (item in snapshot.children) {
                    // Safe casting with Map
                    val song = item.value as? Map<*, *> ?: continue
                    val isGlorifyingSong = song["glorifyingSong"] as? Boolean ?: false
                    val isWorshipSong = song["worshipSong"] as? Boolean ?: false
                    val isGiftSong = song["giftSong"] as? Boolean ?: false
                    val isFromSongbookSong = song["fromSongbookSong"] as? Boolean ?: false

                    val title = song["title"] as? String ?: ""
                    val tonality = song["tonality"] as? String ?: ""
                    val words = song["words"] as? String ?: ""
                    val temp = song["temp"] as? String ?: ""
                    val id = item.key ?: ""

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
                    allSongList.add(songObj)
                }

                continuation.resume(allSongList)
            }

            override fun onCancelled(error: DatabaseError) {
                continuation.resumeWithException(error.toException())
            }
        }

        databaseRef.addListenerForSingleValueEvent(vListener)

        continuation.invokeOnCancellation {
            databaseRef.removeEventListener(vListener)
        }
    }
}
