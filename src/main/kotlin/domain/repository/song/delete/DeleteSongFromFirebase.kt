package domain.repository.song.delete

import domain.model.Song

interface DeleteSongFromFirebase {
    fun deleteSongFromFirebase(song: Song)
}