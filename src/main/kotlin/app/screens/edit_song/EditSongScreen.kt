package app.screens.edit_song

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.input.TextFieldValue
import app.dialog.song.TaskCompletedSuccessful
import app.screens.add_new_song.SongSettingScreen
import app.style.appBg
import app.style.appSecondaryColor
import app.widgets.MenuArrowBack
import data.lambda.song.makeSong.makeSong
import data.lambda.song.update.onUpdateSongItem
import domain.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun EditSongScreen(song: Song, isShowEditSongScreen: MutableState<Boolean>) {
    MainContent(song, isShowEditSongScreen)
}


@Composable
private fun MainContent(song: Song, isShowEditSongScreen: MutableState<Boolean>) {
    Row(modifier = Modifier.fillMaxSize().background(appBg)) {
        val songTitle = remember { mutableStateOf(TextFieldValue().copy(text = song.title)) }
        val songTonality = remember { mutableStateOf(TextFieldValue().copy(text = song.tonality)) }
        val songTemp = remember { mutableStateOf(TextFieldValue()/*.copy(text = song.temp)*/) }
        val songWords = remember { mutableStateOf(TextFieldValue().copy(text = song.words)) }

        val songIsGlorifyingSong = remember { mutableStateOf(song.isGlorifyingSong) }
        val songIsWorshipSong = remember { mutableStateOf(song.isWorshipSong) }
        val songIsGiftSong = remember { mutableStateOf(song.isGiftSong) }
        val songIsFromSongbookSong = remember { mutableStateOf(song.isFromSongbookSong) }

        val isShowSuccessfulDialog = remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()


        Column(modifier = Modifier.fillMaxWidth(0.8f).drawBehind {
            val x = size.width
            val y = size.height
            drawLine(color = appSecondaryColor, start = Offset(x, 0f), end = Offset(x, y), strokeWidth = 2f)
        }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                MenuArrowBack(isShowEditSongScreen)
                app.widgets.TextField(placeholder = "Վերնագիր․․․", songTitle, textStyle = MaterialTheme.typography.h6,)
            }
            app.widgets.TextField(placeholder = "Բառեր․․․",songWords,textStyle = MaterialTheme.typography.h6)
        }

        SongSettingScreen(
            songTonality, songTemp,
            songIsGlorifyingSong, songIsWorshipSong, songIsGiftSong, songIsFromSongbookSong
        ) {
            val newSong = makeSong(
                song.id,
                songTitle.value.text,
                songTonality.value.text,
                songWords.value.text,
                songTemp.value.text.toInt(),
                songIsGlorifyingSong.value,
                songIsWorshipSong.value,
                songIsGiftSong.value,
                songIsFromSongbookSong.value
            )
            println(newSong)
            onUpdateSongItem(song, newSong)
            isShowSuccessfulDialog.value = true
            scope.launch {
                delay(2000)
                isShowEditSongScreen.value =false
            }
        }
        if (isShowSuccessfulDialog.value) TaskCompletedSuccessful(isShowSuccessfulDialog)

    }
}


