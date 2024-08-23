package data.lambda.template.update

import app.di.AppComponent
import domain.model.SongTemplate

val onUpdateSongTemplateItem = { updatedSong: SongTemplate ,newTemplate: SongTemplate ->
    AppComponent.updateSongTemplateInFirebaseUseCase.execute(updatedSong, newTemplate)
}
