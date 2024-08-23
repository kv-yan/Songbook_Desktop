package app.screens.add_new_song

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.di.AppComponent
import app.dialog.snackbar.AppSnackbar
import app.style.appBg
import app.style.appSecondaryColor
import app.style.appTextColor
import app.widgets.CheckboxWithText
import app.widgets.TextField
import data.lambda.song.makeSong.makeSong
import domain.model.Song


enum class NewSongFieldState(val msg: String, val bgColor: Color) {
    INVALID_TITLE("Լրացրեք 'Վերնագիր' բաժինը", Color.Red), INVALID_WORDS(
        "Լրացրեք 'Բառեր' բաժինը", Color.Red
    ),
    INVALID_TONALITY("Լրացրեք 'Տոն' բաժինը", Color.Red), INVALID_TEMP(
        "Լրացրեք 'Տեմպ' բաժինը", Color.Red
    ),
    INVALID_CATEGORY(
        "Նշեք թէ երգը որ տեսակին է պատկանում․ \nԵրգը պոտք է լինի կամ 'փառաբանություն' կամ 'Երկրպագություն'", Color.Red
    ),
    DONE("Երգը հաջողությամբ պահպանվել է", appSecondaryColor),
}

@Composable
fun NewSongScreen(newSongTitle: MutableState<TextFieldValue>, newSongWords: MutableState<TextFieldValue>) {
    val isSongSavedCorrectly = remember { mutableStateOf(false) }
    val newSongFieldState = remember { mutableStateOf(NewSongFieldState.INVALID_CATEGORY) }

    Box {
        MainContent(
            isSongSavedCorrectly = isSongSavedCorrectly,
            newSongFieldState = newSongFieldState,
            songTitle = newSongTitle,
            songWords = newSongWords
        )

        AppSnackbar(isSongSavedCorrectly, Modifier.offset(y = 40.dp).padding(end = 24.dp)) {
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.4f)
                    .background(newSongFieldState.value.bgColor, RoundedCornerShape(8.dp)).padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(if (newSongFieldState.value == NewSongFieldState.DONE) "ic_done.png" else "ic_error.png"),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = newSongFieldState.value.msg,
                    style = MaterialTheme.typography.subtitle1,
                    color = appTextColor,
                    modifier = Modifier.fillMaxWidth()
                )

            }
        }
    }
}


@Composable
private fun MainContent(
    isSongSavedCorrectly: MutableState<Boolean>,
    newSongFieldState: MutableState<NewSongFieldState>,
    songTitle: MutableState<TextFieldValue>,
    songWords: MutableState<TextFieldValue>,
) {


    Row(modifier = Modifier.fillMaxSize().background(appBg)) {
        val songTonality = remember { mutableStateOf(TextFieldValue()) }
        val songTemp = remember { mutableStateOf(TextFieldValue()) }

        val songIsGlorifyingSong = remember { mutableStateOf(false) }
        val songIsWorshipSong = remember { mutableStateOf(false) }
        val songIsGiftSong = remember { mutableStateOf(false) }
        val songIsFromSongbookSong = remember { mutableStateOf(false) }

        Column(modifier = Modifier.fillMaxWidth(0.8f).drawBehind {
            val x = size.width
            val y = size.height
            drawLine(color = appSecondaryColor, start = Offset(x, 0f), end = Offset(x, y), strokeWidth = 2f)
        }) {
            TextField(placeholder = "Վերնագիր․․․", songTitle, textStyle = MaterialTheme.typography.h6)

            TextField(placeholder = "Բառեր․․․", songWords, textStyle = MaterialTheme.typography.h6)
        }
        SongSettingScreen(
            songTonality, songTemp, songIsGlorifyingSong, songIsWorshipSong, songIsGiftSong, songIsFromSongbookSong
        ) {
            val newSong = makeSong(
                title = songTitle.value.text,
                tonality = songTonality.value.text,
                words = songWords.value.text,
                temp = songTemp.value.text,
                isGlorifyingSong = songIsGlorifyingSong.value,
                isWorshipSong = songIsWorshipSong.value,
                isGiftSong = songIsGiftSong.value,
                isFromSongbookSong = songIsFromSongbookSong.value
            )


            savingLogic(newSong, isSongSavedCorrectly, newSongFieldState) {
                cleanFieldsValues(
                    songTitle = songTitle,
                    songWords = songWords,
                    songTonality = songTonality,
                    songTemp = songTemp,
                    songIsGlorifyingSong = songIsGlorifyingSong,
                    songIsWorshipSong = songIsWorshipSong,
                    songIsGiftSong = songIsGiftSong,
                    songIsFromSongbookSong = songIsFromSongbookSong
                )

            }
        }

    }
//    SongSavedSuccessfully(isSongSavedCorrectly)
}

fun cleanFieldsValues(
    songTitle: MutableState<TextFieldValue>,
    songTonality: MutableState<TextFieldValue>,
    songWords: MutableState<TextFieldValue>,
    songTemp: MutableState<TextFieldValue>,
    songIsGlorifyingSong: MutableState<Boolean>,
    songIsWorshipSong: MutableState<Boolean>,
    songIsGiftSong: MutableState<Boolean>,
    songIsFromSongbookSong: MutableState<Boolean>,
) {
    songTitle.value = songTitle.value.copy(text = "")
    songTonality.value = songTonality.value.copy(text = "")
    songWords.value = songWords.value.copy(text = "")
    songTemp.value = songTemp.value.copy(text = "")

    songIsGlorifyingSong.value = false
    songIsWorshipSong.value = false
    songIsGiftSong.value = false
    songIsFromSongbookSong.value = false
}


@Composable
fun SongSettingScreen(
    songTonality: MutableState<TextFieldValue>,
    songTemp: MutableState<TextFieldValue>,
    songIsGlorifying: MutableState<Boolean>,
    songIsWorship: MutableState<Boolean>,
    songIsGift: MutableState<Boolean>,
    songIsFromSongbook: MutableState<Boolean>,
    onSaveClick: () -> Unit,
) {
    Column {
        TextField(placeholder = "Տոն․․․", songTonality)
        TextField(placeholder = "Տեմպ․․․", songTemp)

        Column {
            CheckboxWithText("Փառաբանություն", songIsGlorifying)
            CheckboxWithText("Երկրպագություն", songIsWorship)
            CheckboxWithText("Ընծա", songIsGift)
            CheckboxWithText("Երգարանային", songIsFromSongbook)

            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.background(appBg).fillMaxSize()) {
                Button(
                    onClick = {
                        onSaveClick()
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

fun savingLogic(
    newSong: Song,
    showDialog: MutableState<Boolean>,
    newSongFieldState: MutableState<NewSongFieldState>,
    onCompleted: () -> Unit,
) {
    if (newSong.title.isEmpty()) {
        newSongFieldState.value = NewSongFieldState.INVALID_TITLE
    } else if (newSong.words.isEmpty()) {
        newSongFieldState.value = NewSongFieldState.INVALID_WORDS
    } else if (newSong.tonality.isEmpty()) {
        newSongFieldState.value = NewSongFieldState.INVALID_TONALITY
    } else if (newSong.temp.toInt() == 0 || newSong.temp.toInt() < 0) {
        newSongFieldState.value = NewSongFieldState.INVALID_TEMP
    } else if (!newSong.isGlorifyingSong && !newSong.isWorshipSong) {
        newSongFieldState.value = NewSongFieldState.INVALID_CATEGORY
    } else {
        AppComponent.saveSongToFirebaseUseCase.saveSongToFirebase.saveSong(newSong)
        onCompleted.invoke()
        newSongFieldState.value = NewSongFieldState.DONE
    }
    showDialog.value = true
}