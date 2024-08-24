package data.repository.template.get

import com.google.firebase.database.*
import domain.model.Song
import domain.model.SongTemplate
import domain.repository.template.get.GetTemplatesFromFirebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GetTemplatesFromFirebaseImpl : GetTemplatesFromFirebase {
    private val mDataBase: DatabaseReference = FirebaseDatabase.getInstance().getReference("SongsTemplate")

    override suspend fun getTemplates(): List<SongTemplate> = suspendCancellableCoroutine { continuation ->
        val templateList = mutableListOf<SongTemplate>()

        val vListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                templateList.clear() // Clear the list to avoid duplicates
                for (item in snapshot.children) {
                    val template = item.value as? Map<*, *> ?: continue

                    val createDate = template["createDate"] as? String ?: ""
                    val performerName = template["performerName"] as? String ?: ""
                    val weekday = template["weekday"] as? String ?: ""
                    val isSingleMode = template["singleMode"] as? Boolean ?: false

                    val glorifyingSong: List<Song>
                    val worshipSong: List<Song>
                    val giftSong: List<Song>
                    val singleModeSongs: List<Song>

                    if (isSingleMode) {
                        singleModeSongs = getListOfSongs(template["singleModeSongs"])
                        glorifyingSong = listOf()
                        worshipSong = listOf()
                        giftSong = listOf()
                    } else {
                        singleModeSongs = listOf()
                        glorifyingSong = getListOfSongs(template["glorifyingSong"])
                        worshipSong = getListOfSongs(template["worshipSong"])
                        giftSong = getListOfSongs(template["giftSong"])
                    }

                    templateList.add(
                        SongTemplate(
                            id = item.key ?: "",
                            createDate = createDate,
                            performerName = performerName,
                            weekday = weekday,
                            isSingleMode = isSingleMode,
                            glorifyingSong = glorifyingSong,
                            worshipSong = worshipSong,
                            giftSong = giftSong,
                            singleModeSongs = singleModeSongs
                        )
                    )
                }
                continuation.resume(templateList)
            }

            override fun onCancelled(error: DatabaseError) {
                continuation.resumeWithException(error.toException())
            }
        }

        mDataBase.addValueEventListener(vListener)

        continuation.invokeOnCancellation {
            mDataBase.removeEventListener(vListener)
        }
    }

    private fun getListOfSongs(songData: Any?): List<Song> {
        val songList = mutableListOf<Song>()

        // Check if the data is a list of maps
        (songData as? List<*>)?.let { list ->
            list.forEach { item ->
                // Check if each item is a map
                (item as? Map<*, *>)?.let { map ->
                    try {
                        songList.add(
                            Song(
                                id = map["id"] as? String ?: "",
                                title = map["title"] as? String ?: "",
                                tonality = map["tonality"] as? String ?: "",
                                words = map["words"] as? String ?: "",
                                temp = map["temp"] as? String ?: "",
                                isGlorifyingSong = map["glorifyingSong"] as? Boolean ?: false,
                                isWorshipSong = map["worshipSong"] as? Boolean ?: false,
                                isGiftSong = map["giftSong"] as? Boolean ?: false,
                                isFromSongbookSong = map["fromSongbookSong"] as? Boolean ?: false,
                                isUsingSoundTrack = map["usingSoundTrack"] as? Boolean ?: false
                            )
                        )
                    } catch (e: Exception) {
                        // Handle or log the error if needed
                    }
                }
            }
        }

        return songList
    }
}
