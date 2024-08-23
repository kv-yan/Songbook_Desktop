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
        var templateList = mutableListOf<SongTemplate>()
        lateinit var id: String
        lateinit var createDate: String
        lateinit var performerName: String
        lateinit var weekday: String
        var favorite: Boolean
        var glorifyingSong: List<Song>
        var worshipSong: List<Song>
        var giftSong: List<Song>
        try {
            val vListener: ValueEventListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    if (templateList.size > 0) templateList.clear()
                    for (item: DataSnapshot in p0.children) {
                        val template: HashMap<String, Any> = item.value as HashMap<String, Any>
                        val createDate = template["createDate"] as String
                        val performerName = template["performerName"] as String
                        val weekday = template["weekday"] as String
                        val isSingleMode = template["singleMode"] as Boolean
                        var glorifyingSong: List<Song>
                        var worshipSong: List<Song>
                        var giftSong: List<Song>
                        var singleModeSongs: List<Song>

                        if (isSingleMode) {
                            singleModeSongs = getListOfSongs(template["singleModeSongs"] as ArrayList<HashMap<Any, Any>>)
                            glorifyingSong = listOf<Song>()
                            worshipSong = listOf<Song>()
                            giftSong = listOf<Song>()
                        } else {
                            singleModeSongs = listOf<Song>()
                            glorifyingSong =
                                getListOfSongs(template["glorifyingSong"] as ArrayList<HashMap<Any, Any>>)
                            worshipSong =
                                getListOfSongs(template["worshipSong"] as ArrayList<HashMap<Any, Any>>)
                            giftSong =
                                getListOfSongs(template["giftSong"] as ArrayList<HashMap<Any, Any>>)
                        }


                        templateList.add(
                            SongTemplate(
                                id = item.key.toString(),
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

                override fun onCancelled(p0: DatabaseError) {
                    continuation.resumeWithException(p0.toException())
                }
            }
            mDataBase.addValueEventListener(vListener)

            continuation.invokeOnCancellation {
                mDataBase.removeEventListener(vListener)
            }

        } catch (ex: NoSuchElementException) {
            templateList = mutableListOf()
        }
    }


    private fun getListOfSongs(song: ArrayList<HashMap<Any, Any>>): List<Song> {
        val songList = mutableListOf<Song>()
        for (item in song) {
            val id = item["id"] as String
            val title = item["title"] as String
            val tonality = item["tonality"] as String
            val words = item["words"] as String
            val temp = item["temp"] as String
            val isGlorifyingSong = item["glorifyingSong"] as Boolean
            val isWorshipSong = item["worshipSong"] as Boolean
            val isGiftSong = item["giftSong"] as Boolean
            val isFromSongbookSong = item["fromSongbookSong"] as Boolean
            val isUsingSoundTrack = try { item["usingSoundTrack"] as Boolean
            } catch (ex : NullPointerException){
                false
            }
            songList.add(
                Song(
                    id = id,
                    title = title,
                    tonality = tonality,
                    words = words,
                    temp = temp,
                    isGlorifyingSong = isGlorifyingSong,
                    isWorshipSong = isWorshipSong,
                    isGiftSong = isGiftSong,
                    isFromSongbookSong = isFromSongbookSong,
                    isUsingSoundTrack = isUsingSoundTrack
                )
            )
        }
        return songList
    }
}