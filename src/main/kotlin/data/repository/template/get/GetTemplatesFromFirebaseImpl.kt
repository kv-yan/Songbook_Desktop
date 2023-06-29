package data.repository.template.get

import com.google.firebase.database.*
import domain.model.Song
import domain.model.SongTemplate
import domain.repository.template.get.GetTemplatesFromFirebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GetTemplatesFromFirebaseImpl : GetTemplatesFromFirebase {
    private val mDataBase: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("SongsTemplate")

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
                        id = item.key
                        createDate = template.getValue("createDate") as String
                        performerName = template.getValue("performerName") as String
                        weekday = template.getValue("weekday") as String
                        favorite = template.getValue("favorite") as Boolean
                        glorifyingSong =
                            getListOfSongs(template.getValue("glorifyingSong") as ArrayList<HashMap<Any, Any>>)
                        worshipSong = getListOfSongs(template.getValue("worshipSong") as ArrayList<HashMap<Any, Any>>)
                        giftSong = getListOfSongs(template.getValue("giftSong") as ArrayList<HashMap<Any, Any>>)
                        templateList.add(
                            SongTemplate(
                                /*"SongTemplate"*/id,
                                createDate,
                                performerName,
                                weekday,
                                favorite,
                                glorifyingSong,
                                worshipSong,
                                giftSong
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
            lateinit var title: String
            lateinit var tonality: String
            lateinit var words: String
            var isGlorifyingSong: Boolean
            var isWorshipSong: Boolean
            var isGiftSong: Boolean
            var isFromSongbookSong: Boolean
            val songItem: HashMap<Any, Any> = item
            title = songItem.getValue("title") as String
            tonality = songItem.getValue("tonality") as String
            words = songItem.getValue("words") as String
            isGlorifyingSong = songItem.getValue("glorifyingSong") as Boolean
            isWorshipSong = songItem.getValue("worshipSong") as Boolean
            isGiftSong = songItem.getValue("giftSong") as Boolean
            isFromSongbookSong = songItem.getValue("fromSongbookSong") as Boolean
            mDataBase.key?.let {
                Song(
                    it, title, tonality, words, isGlorifyingSong,
                    isWorshipSong, isGiftSong, isFromSongbookSong
                )
            }?.let {
                songList.add(it)
            }
        }
        return songList
    }
}