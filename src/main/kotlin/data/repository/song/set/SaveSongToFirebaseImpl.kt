package data.repository.song.set

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import domain.model.Song
import domain.repository.song.set.SaveSongToFirebase

class SaveSongToFirebaseImpl : SaveSongToFirebase {
    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Song")

    override fun saveSong(song: Song) {
        try {
            databaseRef.push().setValue(song, null)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }
}
