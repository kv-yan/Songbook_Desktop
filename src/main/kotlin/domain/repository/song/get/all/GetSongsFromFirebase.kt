package domain.repository.song.get.all

import domain.model.Song

interface GetSongsFromFirebase {
    suspend fun getSongs(): List<Song>
}