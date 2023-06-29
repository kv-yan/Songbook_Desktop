package domain.useCase.song.delete

import domain.model.Song
import domain.repository.song.delete.DeleteSongFromFirebase

class DeleteSongFromFirebaseUseCase(private val deleteSongFromFirebase: DeleteSongFromFirebase) {
    fun execute(song: Song){
        deleteSongFromFirebase.deleteSongFromFirebase(song)
    }
}