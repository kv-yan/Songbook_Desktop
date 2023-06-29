package domain.model

data class Song(
    var id: String,
    var title: String,
    var tonality: String,
//    val temp: Int,
    var words: String,
    var isGlorifyingSong: Boolean,
    var isWorshipSong: Boolean,
    var isGiftSong: Boolean,
    var isFromSongbookSong: Boolean
)

