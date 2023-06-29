package data.lambda.template.delete

import app.di.AppComponent
import domain.model.SongTemplate

val onDeleteSongTemplateItem = { template: SongTemplate ->
    AppComponent.deleteSongTemplatesFromFirebaseUseCase.execute(template)
}
