package domain.repository.song.get.glorifying

import domain.model.Song

interface GetGlorifyingSongsFromFirebase {
    suspend fun getGlorifyingSongsFromFirebase():List<Song>
}