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
            override fun onDataChange(snapshot: DataSnapshot) {
                worshipSongs.clear() // Clear the list to avoid duplicates
                for (item in snapshot.children) {
                    // Safe casting to Map<*, *>
                    val song = item.value as? Map<*, *> ?: continue

                    // Use safe casting and provide default values
                    val isGlorifyingSong = song["glorifyingSong"] as? Boolean ?: false
                    val isWorshipSong = song["worshipSong"] as? Boolean ?: false
                    val isGiftSong = song["giftSong"] as? Boolean ?: false
                    val isFromSongbookSong = song["fromSongbookSong"] as? Boolean ?: false

                    if (isGlorifyingSong || isWorshipSong || isGiftSong || isFromSongbookSong) {
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
                        if (songObj.isWorshipSong) { // Check the specific criteria for worship songs
                            worshipSongs.add(songObj)
                        }
                    }
                }
                continuation.resume(worshipSongs)
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
