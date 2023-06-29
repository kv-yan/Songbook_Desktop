package domain.repository.song.update

import domain.model.Song

interface UpdateSongInFirebase {
    fun updateSong(song: Song, updatedSong: Song)
}