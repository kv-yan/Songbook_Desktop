package domain.repository.template.delete

import domain.model.SongTemplate

interface DeleteSongTemplateFromFirebase {
    fun deleteSongTemplateFromFirebase(template: SongTemplate)
}