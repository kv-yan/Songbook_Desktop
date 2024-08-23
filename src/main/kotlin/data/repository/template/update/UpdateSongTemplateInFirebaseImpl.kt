package data.repository.template.update

import com.google.firebase.database.FirebaseDatabase
import domain.model.Song
import domain.model.SongTemplate
import domain.repository.template.update.UpdateSongTemplateInFirebase

class UpdateSongTemplateInFirebaseImpl : UpdateSongTemplateInFirebase {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("SongsTemplate")
    override fun updateSong(template: SongTemplate, newTemplate: SongTemplate) {
        val templateId = template.id
        val templateRef = databaseRef.child(templateId)

        val updatedValues = mapOf("createDate" to newTemplate.createDate,
            "performerName" to newTemplate.performerName,
            "weekday" to newTemplate.weekday,
            "favorite" to newTemplate.isSingleMode,
            "glorifyingSong" to newTemplate.glorifyingSong.map { it.toMap() },
            "worshipSong" to newTemplate.worshipSong.map { it.toMap() },
            "giftSong" to newTemplate.giftSong.map { it.toMap() },
            "singleModeSongs" to newTemplate.singleModeSongs.map { it.toMap() })

        templateRef.updateChildren(updatedValues) { error, ref ->
            if (error != null) {
                println("Failed to update template: ${error.message}")
            } else {
                println("Template updated successfully at $ref")
            }
        }

    }
}

fun Song.toMap(): HashMap<String, Any?> {
    return hashMapOf(
        "id" to id,
        "title" to title,
        "tonality" to tonality,
        "words" to words,
        "temp" to temp,
        "glorifyingSong" to isGlorifyingSong,
        "worshipSong" to isWorshipSong,
        "giftSong" to isGiftSong,
        "fromSongbookSong" to isFromSongbookSong
    )
}