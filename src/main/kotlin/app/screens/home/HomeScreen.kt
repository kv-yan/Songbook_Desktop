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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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
    val searchText = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val loadingSongs = remember { mutableStateOf(true) }

    fun showLoading(){
        screenSongs.value = mutableListOf()
        allSongs.value = mutableListOf()
        loadingSongs.value = true
    }

    LaunchedEffect(loadingSongs.value) {
        if (loadingSongs.value)
            scope.launch {
                allSongs.value = AppComponent.getSongsFromFirebaseUseCase.execute().toMutableList()
                allSongs.value.sortBy { it.title.lowercase() }
                screenSongs.value = allSongs.value
                loadingSongs.value = false
            }
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
            SingleSongScreen(
                song = selectedSongItem.value,
                isShowSingleSong = isShowSingleSong,
                onEditClick = {
                    onEditSong(isShowEditSongScreen)
                    editSongItem.value = selectedSongItem.value
                    isShowSingleSong.value = false
                },
                onExitScreen = {
                    isShowSingleSong.value = false
                    showLoading()
                }
            )
        } else if (isShowEditSongScreen.value) {
            EditSongScreen(editSongItem.value, isShowEditSongScreen)
        } else {
            SearchView(searchText) { query ->
                screenSongs.value = searchSongByContains(allSongs.value, query).toMutableList()
            }

            if (allSongs.value.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    items(screenSongs.value) { songItem ->
                        Column(modifier = Modifier.clickable {
                            selectedSongItem.value = songItem
                            isShowSingleSong.value = !isShowSingleSong.value
                        }) {
                            SongsColumItem(song = songItem, clickedItem = editSongItem, onEditingItem = {
                                onEditSong(isShowEditSongScreen)
                            }, onDeleteCompleted = {
                                showLoading()
                            })
                        }
                    }
                }
            } else if (searchText.value.isNotEmpty() && allSongs.value.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Ոչինչ չի գտնվել \'${searchText.value}\' ",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )

            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    items(8) {
                        SongShimmerAnimation()
                    }
                }
            }
        }
    }
}


val onEditSong = { isEditedSong: MutableState<Boolean> ->
    isEditedSong.value = true
}

