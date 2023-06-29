package domain.repository.song.get.worship

import domain.model.Song

interface GetWorshipSongsFromFirebase {
    suspend fun getWorshipSongsFromFirebase(): List<Song>
}