package app.additional


import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import java.io.FileInputStream
import java.io.InputStream

fun initFirebase() {
    val serviceAccountPath = "C:\\Users\\varda\\OneDrive\\Desktop\\bethel\\Songbook_Desktop_test\\src\\main\\resources\\songs-22ede-firebase-adminsdk-68cqx-571ba0ff25.json"
    val serviceAccount: InputStream = FileInputStream(serviceAccountPath)

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://songs-22ede-default-rtdb.firebaseio.com")
        .build()

    // Initialize FirebaseApp only if it's not already initialized
    if (FirebaseApp.getApps().isEmpty()) {
        FirebaseApp.initializeApp(options)
    }
}
