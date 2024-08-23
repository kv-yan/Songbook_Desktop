package domain.useCase.template.update

import domain.model.SongTemplate
import domain.repository.template.update.UpdateSongTemplateInFirebase

class UpdateSongTemplateInFirebaseUseCase(private val updateSongTemplateInFirebase: UpdateSongTemplateInFirebase) {
    fun execute(template: SongTemplate, newTemplate: SongTemplate) {
        updateSongTemplateInFirebase.updateSong(template = template, newTemplate = newTemplate)
    }
}