package data.repository.template.delete

import com.google.firebase.database.FirebaseDatabase
import domain.model.SongTemplate
import domain.repository.template.delete.DeleteSongTemplateFromFirebase

class DeleteSongTemplateFromFirebaseImpl : DeleteSongTemplateFromFirebase {
    private val songsRef = FirebaseDatabase.getInstance().getReference("SongsTemplate")
    override fun deleteSongTemplateFromFirebase(template: SongTemplate) {
        try {
            songsRef.child(template.id).removeValue(null)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }
}