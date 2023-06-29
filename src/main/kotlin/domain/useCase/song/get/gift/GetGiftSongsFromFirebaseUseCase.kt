package domain.useCase.song.get.gift

import domain.model.Song
import domain.repository.song.get.gift.GetGiftSongsFromFirebase

class GetGiftSongsFromFirebaseUseCase(private val getSongsFromFirebase: GetGiftSongsFromFirebase) {
    suspend fun execute(): List<Song> {
        return getSongsFromFirebase.getGiftSongsFromFirebase()
    }
}