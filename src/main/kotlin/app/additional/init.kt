package app.additional

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import domain.model.Song
import domain.model.SongTemplate
import java.io.FileInputStream

fun initFirebase() {
    val serviceAccount =
        FileInputStream("src\\main\\kotlin\\app\\additional\\songs-22ede-firebase-adminsdk-68cqx-571ba0ff25.json")
    val options = FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://songs-22ede-default-rtdb.firebaseio.com").build()
    FirebaseApp.initializeApp(options)
    println("initializeApp :: SUCCESSFUL")
}




val testSong = Song(
    id = "Song",
    title = "Ուղղակի վերնագիր",
    tonality = "Ուղղակի տոնայնություն",
    words = "Ուղղակի Բառեր".repeat(20),
    isGlorifyingSong = false,
    isWorshipSong = true,
    isGiftSong = true,
    isFromSongbookSong = true
)
val testTemplate = SongTemplate(
    id = "SongTemplate",
    createDate = "09.06-2003",
    performerName = "Կարեն",
    weekday = "Երկուշաբթի",
    favorite = false,
    glorifyingSong = emptyList(),
    worshipSong = emptyList(),
    giftSong = emptyList()
)