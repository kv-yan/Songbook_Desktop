package app.screens.add_new_song

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.di.AppComponent
import app.style.appBg
import app.style.appSecondaryColor
import app.widgets.CheckboxWithText
import app.widgets.RadioButtonWithText
import app.widgets.TextField
import data.lambda.song.makeSong.makeSong
import domain.model.Song

@Composable
fun NewSongScreen() {
    MainContent()
}


@Composable
private fun MainContent() {
    Row(modifier = Modifier.fillMaxSize().background(appBg)) {
        val songTitle = remember { mutableStateOf(TextFieldValue()) }
        val songTonality = remember { mutableStateOf(TextFieldValue()) }
        val songTemp = remember { mutableStateOf(TextFieldValue()) }
        val songWords = remember { mutableStateOf(TextFieldValue()) }

        val songIsGlorifyingSong = remember { mutableStateOf(false) }
        val songIsWorshipSong = remember { mutableStateOf(false) }
        val songIsGiftSong = remember { mutableStateOf(false) }
        val songIsFromSongbookSong = remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxWidth(0.8f).drawBehind {
            val x = size.width
            val y = size.height
            drawLine(color = appSecondaryColor, start = Offset(x, 0f), end = Offset(x, y), strokeWidth = 2f)
        }) {
            TextField(placeholder = "Վերնագիր․․․", songTitle, textStyle = MaterialTheme.typography.h6,)

        TextField(placeholder = "Բառեր․․․",songWords,textStyle = MaterialTheme.typography.h6)
        }
        SongSettingScreen(
            songTonality, songTemp,
            songIsGlorifyingSong, songIsWorshipSong, songIsGiftSong, songIsFromSongbookSong
        ) {
            val newSong = makeSong(
                songTitle.value.text,
                songTonality.value.text,
                songWords.value.text,
                songIsGlorifyingSong.value,
                songIsWorshipSong.value,
                songIsGiftSong.value,
                songIsFromSongbookSong.value
            )
            savingLogic(newSong)
        }
    }
}


@Composable
fun SongSettingScreen(
    songTonality: MutableState<TextFieldValue>,
    songTemp: MutableState<TextFieldValue>,
    songIsGlorifying: MutableState<Boolean>,
    songIsWorship: MutableState<Boolean>,
    songIsGift: MutableState<Boolean>,
    songIsFromSongbook: MutableState<Boolean>,
    onClick: () -> Unit
) {
    Column {
        app.widgets.TextField(placeholder = "Տոն․․․", songTonality)
        app.widgets.TextField(placeholder = "Տեմպ․․․", songTemp)

        Column {
            RadioButtonWithText("Փառաբանություն", songIsGlorifying, songIsWorship)
            RadioButtonWithText("Երկրպագություն", songIsWorship, songIsGlorifying)
            CheckboxWithText("Ընծա", songIsGift)
            CheckboxWithText("Երգարանային", songIsFromSongbook)

            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.background(appBg).fillMaxSize()) {
                Button(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = appSecondaryColor)
                ) {
                    Text(text = "Պահպանել", style = MaterialTheme.typography.subtitle1, color = Color.White)
                }
            }
        }
    }
}

fun savingLogic(newSong: Song) {
    if (newSong.title.isEmpty()) {
        println("error :: empty title ")
        println("-----------------------------------")
        // TODO: setError
    } else if (newSong.tonality.isEmpty()) {
        println("error :: empty tonality ")
        println("-----------------------------------")
        // TODO: setError
    } else if (newSong.words.isEmpty()) {
        println("error :: empty words ")
        println("-----------------------------------")
        // TODO: setError
    } /*else if (newSong.temp.isEmpty()) {
        println("error :: empty temp ")
        println("-----------------------------------")
        // TODO: setError
    }*/ else if (!newSong.isGlorifyingSong && !newSong.isWorshipSong) {
        println("error :: select song type ")
        println("-----------------------------------")
        // TODO: setError
        // TODO: hint user for check song type
    } else {
        AppComponent.saveSongToFirebaseUseCase.saveSongToFirebase.saveSong(newSong)
        println("Saved new song ")
    }
}