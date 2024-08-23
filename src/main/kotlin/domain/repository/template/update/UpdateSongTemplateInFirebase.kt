package domain.repository.template.update

import domain.model.SongTemplate

interface UpdateSongTemplateInFirebase {
    fun updateSong(template: SongTemplate, newTemplate: SongTemplate)
}