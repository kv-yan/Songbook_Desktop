package data.repository.song.delete

import com.google.firebase.database.FirebaseDatabase
import domain.model.Song
import domain.repository.song.delete.DeleteSongFromFirebase

class DeleteSongFromFirebaseImpl : DeleteSongFromFirebase {
    val songsRef = FirebaseDatabase.getInstance().getReference("Song")
    override fun deleteSongFromFirebase(song: Song) {
        try {
            songsRef.child(song.id).removeValue(null)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }
}