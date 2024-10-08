package app.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.di.AppComponent
import app.items.songs.SongsColumItem
import app.screens.edit_song.EditSongScreen
import app.screens.single_song.SingleSongScreen
import app.style.appBg
import app.widgets.SearchView
import app.widgets.SongShimmerAnimation
import data.lambda.search.searchSongByContains
import domain.model.Song
import kotlinx.coroutines.launch


@Composable
fun HomeScreen() {
    Column(modifier = Modifier.background(Color.Transparent)) {
        val showSingleSong = remember { mutableStateOf(false) }
        val showEditSongScreen = remember { mutableStateOf(false) }
        MainContent(showSingleSong, showEditSongScreen)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Բեթհել երգացուցակ") {
        HomeScreen()
    }
}

@Composable
private fun MainContent(isShowSingleSong: MutableState<Boolean>, isShowEditSongScreen: MutableState<Boolean>) {
    val allSongs = remember { mutableStateOf<MutableList<Song>>(mutableListOf()) }
    val screenSongs = remember { mutableStateOf<MutableList<Song>>(mutableListOf()) }
    val searchText = remember { mutableStateOf(TextFieldValue()) }
    val scope = rememberCoroutineScope()

    scope.launch {
        allSongs.value = AppComponent.getSongsFromFirebaseUseCase.execute().toMutableList()
        screenSongs.value = searchSongByContains(allSongs.value, "").toMutableList()
    }

    Column(modifier = Modifier.background(appBg).fillMaxSize()) {
        val selectedSongItem = remember {
            mutableStateOf(
                Song(
                    id = "404",
                    title = "404",
                    tonality = "404",
                    temp = "404",
                    words = "404",
                    isGlorifyingSong = false,
                    isWorshipSong = false,
                    isGiftSong = false,
                    isFromSongbookSong = false
                )
            )
        }
        val editSongItem = remember {
            mutableStateOf(
                Song(
                    id = "error",
                    title = "error",
                    tonality = "error",
                    words = "error",
                    isGlorifyingSong = false,
                    temp = "404",
                    isWorshipSong = false,
                    isGiftSong = false,
                    isFromSongbookSong = false
                )
            )
        }

        if (isShowSingleSong.value) {
            SingleSongScreen(selectedSongItem.value, isShowSingleSong, onEditClick = {
                onEditSong(isShowEditSongScreen)
                editSongItem.value = selectedSongItem.value
                isShowSingleSong.value = false
            })
        } else if (isShowEditSongScreen.value) {
            EditSongScreen(editSongItem.value, isShowEditSongScreen)
        } else {
            SearchView(searchText) { query ->
                screenSongs.value = searchSongByContains(allSongs.value, query).toMutableList()
            }


            if (allSongs.value.isEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    items(8) {
                        SongShimmerAnimation()
                    }
                }
            } else if (searchText.value.text.isEmpty() && allSongs.value.isEmpty()) {
                Column {
                    Text("Ոչինչ չի գտնվել \'${searchText.value.text}\' ")
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    items(screenSongs.value) { songItem ->
                        Column(modifier = Modifier.clickable {
                            selectedSongItem.value = songItem
                            isShowSingleSong.value = !isShowSingleSong.value

                        }) {
                            SongsColumItem(songItem, editSongItem) {
                                onEditSong(isShowEditSongScreen)
                            }
                        }
                    }
                }
            }
        }
    }
}


val onEditSong = { isEditedSong: MutableState<Boolean> ->
    isEditedSong.value = true
}

