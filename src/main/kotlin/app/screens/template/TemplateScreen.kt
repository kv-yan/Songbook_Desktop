package app.screens.template

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.di.AppComponent
import app.items.template.SongTemplateColumItem
import app.screens.edit_song_template.EditSongTemplateScreen
import app.screens.single_song_template.SingleSongTemplateScreen
import app.style.appBg
import app.style.appSecondaryColor
import app.widgets.SearchView
import app.widgets.TemplateShimmerAnimation
import domain.model.SongTemplate
import kotlinx.coroutines.launch

@Composable
fun TemplateScreen() {
    Column(modifier = Modifier.background(Color.Transparent)) {
        val showSingleSongTemplate = remember { mutableStateOf(false) }
        val showIsEditSongTemplate = remember { mutableStateOf(false) }
        MainContent(showSingleSongTemplate, showIsEditSongTemplate)
    }
}


@Composable
private fun MainContent(
    isShowSingleSongTemplate: MutableState<Boolean>,
    showIsEditSongTemplate: MutableState<Boolean>,
) {

    val selectedTemplateItem = remember {
        mutableStateOf(
            SongTemplate(
                id = "error",
                performerName = "404",
                createDate = "404",
                weekday = "404",
                isSingleMode = false,
                glorifyingSong = emptyList(),
                worshipSong = emptyList(),
                giftSong = emptyList(),
                singleModeSongs = emptyList()
            )
        )
    }
    val searchText = remember { mutableStateOf("") }
    val allSongs = remember { mutableStateOf<List<SongTemplate>>(mutableListOf()) }
    val scope = rememberCoroutineScope()
    val isShowSingleSong = remember { mutableStateOf(false) }


    scope.launch { allSongs.value = AppComponent.getSongTemplatesFromFirebaseUseCase.execute() }
    Column(modifier = Modifier.background(appBg).fillMaxSize()) {
        if (isShowSingleSongTemplate.value) {
            SingleSongTemplateScreen(selectedTemplateItem.value, isShowSingleSongTemplate, isShowSingleSong)
        } else if (showIsEditSongTemplate.value) {
            EditSongTemplateScreen(selectedTemplateItem.value, showIsEditSongTemplate)
        } else {
            SearchView(searchText) {}
            if (allSongs.value.isEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    items(15) {
                        TemplateShimmerAnimation()
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    val state = rememberLazyListState()
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().background(appBg),
                        state = state,
                    ) {
                        items(allSongs.value, key = { it.id }) { menuItem ->
                            Column(modifier = Modifier.clickable {
                                selectedTemplateItem.value = menuItem
                                isShowSingleSongTemplate.value = !isShowSingleSongTemplate.value
                            }) {
                                SongTemplateColumItem(menuItem, showIsEditSongTemplate)
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
            }
        }
    }
}