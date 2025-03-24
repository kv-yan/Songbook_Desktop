package app.screens.home


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.di.AppComponent
import app.items.songs.SongsColumItem
import app.screens.edit_song.EditSongScreen
import app.screens.single_song.SingleSongScreen
import app.style.appBg
import app.style.appSecondaryColor
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

    fun showLoading() {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = "Ընդհ․ ${allSongs.value.size} երգ",
                    color = Color.White,
                    fontSize = 16.sp
                )

                SearchView(searchText) { query ->
                    screenSongs.value = searchSongByContains(allSongs.value, query).toMutableList()
                }
            }

            if (allSongs.value.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    val state = rememberLazyListState()

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        state = state
                    ) {
                        items(screenSongs.value, key = {it.id}) { songItem ->
                            Column(modifier = Modifier.clickable {
                                selectedSongItem.value = songItem
                                isShowSingleSong.value = !isShowSingleSong.value
                            }) {
                                SongsColumItem(
                                    song = songItem,
                                    clickedItem = editSongItem,
                                    onEditingItem = { onEditSong(isShowEditSongScreen) },
                                    onDeleteCompleted = { showLoading() }
                                )
                            }
                        }
                    }

                    VerticalScrollbar(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 2.dp)
                            .fillMaxHeight(),
                        style = ScrollbarStyle(
                            minimalHeight = 16.dp,
                            thickness = 8.dp,
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
                            hoverDurationMillis = 200,
                            unhoverColor = Color.LightGray,
                            hoverColor = appSecondaryColor
                        ),
                        adapter = rememberScrollbarAdapter(
                            scrollState = state,
                        )
                    )
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

