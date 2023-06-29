package domain.repository.template.get

import domain.model.SongTemplate

interface GetTemplatesFromFirebase {
    suspend fun getTemplates(): List<SongTemplate>
}