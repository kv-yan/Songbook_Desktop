package app.screens.add_new_template

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
import app.widgets.TemplateSettingsScreen
import domain.model.Song
import domain.model.SongTemplate
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun NewTemplateScreen() {
    MainContent()
}

@Composable
private fun MainContent() {
    Row(modifier = Modifier.fillMaxSize().background(appBg).padding(25.dp)) {
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
            mutableStateOf(
                mutableStateListOf<Song>()
            )
        }
        val templateWorshipSongs = remember {
            mutableStateOf(mutableStateListOf<Song>())
        }
        val templateGiftSongs = remember {
            mutableStateOf(mutableStateListOf<Song>())
        }
        val templateSingleModeSongs = remember {
            mutableStateOf(mutableStateListOf<Song>())
        }

        val templatePerformerName = remember { mutableStateOf(TextFieldValue()) }
        val templateWeekday = remember { mutableStateOf("") }
        val isSendNotification = remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            scope.launch {
                glorifyingAllSongs.value.addAll(AppComponent.getGlorifyingSongsFromFirebaseUseCase.execute())
                worshipAllSongs.value.addAll(AppComponent.getWorshipSongsFromFirebaseUseCase.execute())
                giftAllSongs.value.addAll(AppComponent.getGiftSongsFromFirebaseUseCase.execute())
            }

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
            Row {// TODO: sett all view to vertical scroll box
                Column(modifier = Modifier.fillMaxHeight()) {
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
            mutableStateOf<Boolean>(!isShowAddSongFunctionality.value)
        }
        var currentDate = remember { mutableStateOf(LocalDateTime.now()) }
        currentDate.value = LocalDateTime.now()


        AnimatedVisibility(visible = isShowAddSongFunctionality.value) {
            isShowTemplateSettings.value = !isShowAddSongFunctionality.value
            CategoriesAddSongsFunctionalityScreen(
                isShowAddSongFunctionality, glorifyingAllSongs.value, addSongTitle, actionAddSong
            )
        }
        val template = remember {
            mutableStateOf(
                SongTemplate(
                    id = "SongTemplate",
                    createDate = currentDate.value.format(DateTimeFormatter.ISO_LOCAL_DATE),
                    performerName = templatePerformerName.value.text,
                    weekday = templateWeekday.value,
                    isSingleMode = false,
                    glorifyingSong = templateGlorifyingSongs.value,
                    worshipSong = templateWorshipSongs.value,
                    giftSong = templateGiftSongs.value,
                    singleModeSongs = templateSingleModeSongs.value

                )
            )
        }
        TemplateSettingsScreen(
            isShowTemplateSettings, templatePerformerName, templateWeekday, isSendNotification, template.value
        )
    }
}
