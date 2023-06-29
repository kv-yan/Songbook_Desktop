package domain.repository.template.set

import domain.model.SongTemplate

interface SaveSongTemplateToFirebase {
    fun saveSongTemplate(songTemplate: SongTemplate)
}