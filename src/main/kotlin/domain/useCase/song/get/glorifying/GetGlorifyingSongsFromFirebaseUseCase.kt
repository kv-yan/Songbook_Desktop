package domain.useCase.song.get.glorifying

import domain.model.Song
import domain.repository.song.get.glorifying.GetGlorifyingSongsFromFirebase

class GetGlorifyingSongsFromFirebaseUseCase(private val getSongsFromFirebase: GetGlorifyingSongsFromFirebase) {
   suspend fun execute(): List<Song> {
        return getSongsFromFirebase.getGlorifyingSongsFromFirebase()
    }
}