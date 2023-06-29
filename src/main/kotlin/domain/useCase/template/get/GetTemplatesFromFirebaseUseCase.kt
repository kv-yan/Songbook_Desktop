package domain.useCase.template.get

import domain.model.SongTemplate
import domain.repository.template.get.GetTemplatesFromFirebase

class GetTemplatesFromFirebaseUseCase(private val getSongsFromFirebase: GetTemplatesFromFirebase) {
    suspend fun execute(): List<SongTemplate> {
        return getSongsFromFirebase.getTemplates()
    }
}