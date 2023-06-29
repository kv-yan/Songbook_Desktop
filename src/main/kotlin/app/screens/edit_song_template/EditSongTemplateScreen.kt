package app.screens.edit_song_template

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.di.AppComponent
import app.style.appBg
import app.style.appSecondaryColor
import app.widgets.CategoriesAddSongsFunctionalityScreen
import app.widgets.CategorySongLabel
import app.widgets.MenuArrowBack
import domain.model.Song
import domain.model.SongTemplate
import kotlinx.coroutines.launch

@Composable
fun EditSongTemplateScreen(songTemplate: SongTemplate, isShowEditSongTemplateScreen: MutableState<Boolean>) {
    MainContent(songTemplate, isShowEditSongTemplateScreen)
}

@Composable
private fun MainContent(songTemplate: SongTemplate, isShowEditSongTemplateScreen: MutableState<Boolean>) {
    val scope = rememberCoroutineScope()
    val glorifyingAllSongs = remember {
        mutableStateOf(
            mutableStateListOf<Song>()
        )
    }
    val worshipAllSongs = remember {
        mutableStateOf(mutableStateListOf<Song>())
    }
    val giftAllSongs = remember {
        mutableStateOf(mutableStateListOf<Song>())
    }

    val templateGlorifyingSongs = remember {
        val empty = mutableStateListOf<Song>()
        songTemplate.glorifyingSong.forEach {
            empty.add(it)
        }
        mutableStateOf(empty)
    }


    val templateWorshipSongs = remember {
        val empty = mutableStateListOf<Song>()
        songTemplate.worshipSong.forEach {
            empty.add(it)
        }
        mutableStateOf(empty)
    }
    val templateGiftSongs = remember {
        val empty = mutableStateListOf<Song>()
        songTemplate.giftSong.forEach {
            empty.add(it)
        }
        mutableStateOf(empty)
    }

    val templatePerformerName = remember { mutableStateOf(TextFieldValue()) }
    val templateWeekday = remember { mutableStateOf("") }
    val isSendNotification = remember { mutableStateOf(false) }




    Row(modifier = Modifier.fillMaxSize().background(appBg).padding(25.dp)) {
        scope.launch {
            glorifyingAllSongs.value.addAll(AppComponent.getGlorifyingSongsFromFirebaseUseCase.execute())
            worshipAllSongs.value.addAll(AppComponent.getWorshipSongsFromFirebaseUseCase.execute())
            giftAllSongs.value.addAll(AppComponent.getGiftSongsFromFirebaseUseCase.execute())
        }

        val isShowAddSongFunctionality = remember {
            mutableStateOf(false)
        }

        val addSongTitle = remember {
            mutableStateOf("Փառաբանությոն")
        }


        val actionAddSong: MutableState<(item: Song) -> Unit> = remember { mutableStateOf({}) }
        Column(modifier = Modifier.fillMaxWidth(0.7f).drawBehind {
            val x = size.width
            val y = size.height
            drawLine(color = appSecondaryColor, start = Offset(x, 0f), end = Offset(x, y), strokeWidth = 2f)
        }) {
            Row {
                Column(modifier = Modifier.fillMaxHeight()) {
                    MenuArrowBack(isShowEditSongTemplateScreen)
                    CategorySongLabel(
                        "Փառաբանություն",
                        templateGlorifyingSongs,
                        isShowAddSongFunctionality,
                        addSongTitle,
                        actionAddSong
                    )
                    CategorySongLabel(
                        "Երկրպագություն", templateWorshipSongs, isShowAddSongFunctionality, addSongTitle, actionAddSong
                    )
                    CategorySongLabel(
                        "Ընծա", templateGiftSongs, isShowAddSongFunctionality, addSongTitle, actionAddSong
                    )
                }
            }
        }
        val isShowTemplateSettings = remember {
            mutableStateOf(!isShowAddSongFunctionality.value)
        }

        AnimatedVisibility(visible = isShowAddSongFunctionality.value) {
            isShowTemplateSettings.value = !isShowAddSongFunctionality.value
            CategoriesAddSongsFunctionalityScreen(
                isShowAddSongFunctionality, glorifyingAllSongs.value, addSongTitle, actionAddSong
            )
        }

        TemplateSettingsScreenForEdit(
            isShowTemplateSettings, templatePerformerName, templateWeekday, isSendNotification, songTemplate
        )
    }

}