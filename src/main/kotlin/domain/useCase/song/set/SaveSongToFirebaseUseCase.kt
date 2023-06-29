package domain.useCase.song.set

import domain.repository.song.set.SaveSongToFirebase
import domain.model.Song

class SaveSongToFirebaseUseCase(val saveSongToFirebase: SaveSongToFirebase) {
     fun execute(newSong: Song) {
        saveSongToFirebase.saveSong(newSong)
    }
}