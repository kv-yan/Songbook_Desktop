package data.lambda.song.delete

import app.di.AppComponent
import domain.model.Song

val onDeleteSongItem = { song: Song ->
    AppComponent.deleteSongFromFirebaseUseCase.execute(song)
}
