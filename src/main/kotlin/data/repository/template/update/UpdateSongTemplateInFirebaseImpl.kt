package data.repository.template.update

import com.google.firebase.database.FirebaseDatabase
import domain.model.SongTemplate
import domain.repository.template.update.UpdateSongTemplateInFirebase

class UpdateSongTemplateInFirebaseImpl : UpdateSongTemplateInFirebase {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("SongsTemplate")
    override fun updateSong(template: SongTemplate) {
        val templateId = template.id
        val templateRef = databaseRef.child(templateId)

        val updatedValues = mapOf(
            "performerName" to template.performerName,
            "weekday" to template.weekday,
            "favorite" to template.favorite,
            "glorifyingSong" to template.glorifyingSong,
            "worshipSong" to template.worshipSong,
            "giftSong" to template.giftSong,
        )

        templateRef.updateChildren(
            updatedValues
        ) { _, _ -> println("completed listener") }

    }
}