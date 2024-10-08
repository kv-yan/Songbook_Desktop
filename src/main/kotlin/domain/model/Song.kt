package domain.model

data class Song(
    var id: String,
    var title: String,
    var tonality: String,
    var words: String,
    var temp: String,
    var isUsingSoundTrack: Boolean = false,
    var isGlorifyingSong: Boolean,
    var isWorshipSong: Boolean,
    var isGiftSong: Boolean,
    var isFromSongbookSong: Boolean,
)