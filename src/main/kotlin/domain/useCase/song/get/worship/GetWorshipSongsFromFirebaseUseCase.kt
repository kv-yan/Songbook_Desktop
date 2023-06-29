package domain.useCase.song.get.worship

import domain.model.Song
import domain.repository.song.get.worship.GetWorshipSongsFromFirebase

class GetWorshipSongsFromFirebaseUseCase(private val getSongsFromFirebase: GetWorshipSongsFromFirebase) {
    suspend fun execute(): List<Song> {
        return getSongsFromFirebase.getWorshipSongsFromFirebase()
    }
}