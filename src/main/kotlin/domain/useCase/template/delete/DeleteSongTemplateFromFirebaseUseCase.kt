package domain.useCase.template.delete

import domain.model.SongTemplate
import domain.repository.template.delete.DeleteSongTemplateFromFirebase

class DeleteSongTemplateFromFirebaseUseCase(private val deleteSongFromFirebase: DeleteSongTemplateFromFirebase) {
    fun execute(template: SongTemplate) {
        deleteSongFromFirebase.deleteSongTemplateFromFirebase(template)
    }
}