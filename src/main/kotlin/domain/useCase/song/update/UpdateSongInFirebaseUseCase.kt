package domain.useCase.song.update

import domain.model.Song
import domain.repository.song.update.UpdateSongInFirebase

class UpdateSongInFirebaseUseCase(private val updateSongInFirebaseUseCase: UpdateSongInFirebase) {

    fun execute(song: Song, updatedSong: Song) {
        updateSongInFirebaseUseCase.updateSong(song, updatedSong)
    }
}