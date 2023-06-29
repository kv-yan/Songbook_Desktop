package domain.useCase.template.set

import domain.model.SongTemplate
import domain.repository.template.set.SaveSongTemplateToFirebase

class SaveSongTemplateInFirebaseUseCase(private val saveSongTemplateToFirebase: SaveSongTemplateToFirebase) {
    fun execute(template: SongTemplate) {
        saveSongTemplateToFirebase.saveSongTemplate(template)
    }
}