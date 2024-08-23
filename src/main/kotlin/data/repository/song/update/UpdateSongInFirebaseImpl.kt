package data.repository.song.update

import com.google.firebase.database.FirebaseDatabase
import domain.model.Song
import domain.repository.song.update.UpdateSongInFirebase

class UpdateSongInFirebaseImpl : UpdateSongInFirebase {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("Song")

    override fun updateSong(song: Song, updatedSong: Song) {
        val songId = song.id
        val songRef = databaseRef.child(songId)

        val updatedValues = mapOf(
            "title" to updatedSong.title,
            "tonality" to updatedSong.tonality,
            "words" to updatedSong.words,
            "temp" to updatedSong.temp,
            "isGlorifyingSong" to updatedSong.isGlorifyingSong,
            "isWorshipSong" to updatedSong.isWorshipSong,
            "isGiftSong" to updatedSong.isGiftSong,
            "isFromSongbookSong" to updatedSong.isFromSongbookSong,
        )

        songRef.updateChildren(
            updatedValues
        ) { _, _ -> }

    }
}
