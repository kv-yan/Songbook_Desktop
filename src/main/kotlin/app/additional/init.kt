package app.additional

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import domain.model.Song
import domain.model.SongTemplate
import java.io.FileInputStream

fun initFirebase() {
    val serviceAccount = FileInputStream("src\\main\\kotlin\\app\\additional\\songs-22ede-firebase-adminsdk-68cqx-571ba0ff25.json")
    val options = FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).setDatabaseUrl("https://songs-22ede-default-rtdb.firebaseio.com").build()
    FirebaseApp.initializeApp(options)
}