package data.lambda.template.set

import app.di.AppComponent
import domain.model.SongTemplate

val onSaveSongTemplateInFirebase = { template: SongTemplate ->
    AppComponent.saveSongTemplateInFirebaseUseCase.execute(template)
}
