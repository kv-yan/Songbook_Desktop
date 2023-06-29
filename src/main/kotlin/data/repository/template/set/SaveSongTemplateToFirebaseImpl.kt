package data.repository.template.set

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import domain.model.SongTemplate
import domain.repository.template.set.SaveSongTemplateToFirebase

class SaveSongTemplateToFirebaseImpl : SaveSongTemplateToFirebase {
    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("SongsTemplate")
    override fun saveSongTemplate(songTemplate: SongTemplate) {
        try {
            databaseRef.push().setValue(songTemplate, null)
            println("saveSongTemplate :: saved new template ")
        } catch (ex: Exception) {
            println(ex.message)
        }

    }
}