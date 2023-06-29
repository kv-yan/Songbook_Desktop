package domain.repository.song.get.gift

import domain.model.Song

interface GetGiftSongsFromFirebase {
    suspend fun getGiftSongsFromFirebase():List<Song>
}