package data.lambda.song.update

import app.di.AppComponent
import domain.model.Song

val onUpdateSongItem = { song: Song, updatedSong: Song ->
    updatedSong.id = song.id
    AppComponent.updateSongFromFirebaseUseCase.execute(song, updatedSong)
}
