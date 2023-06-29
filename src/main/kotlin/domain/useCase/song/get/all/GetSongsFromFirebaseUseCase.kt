package domain.useCase.song.get.all

import domain.model.Song
import domain.repository.song.get.all.GetSongsFromFirebase

class GetSongsFromFirebaseUseCase(private val getSongsFromFirebase: GetSongsFromFirebase) {
   suspend fun execute(): List<Song> {
        return getSongsFromFirebase.getSongs()
    }
}