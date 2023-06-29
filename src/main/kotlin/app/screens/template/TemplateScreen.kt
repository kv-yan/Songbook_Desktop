package app.screens.template

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.di.AppComponent
import app.items.template.SongTemplateColumItem
import app.screens.edit_song_template.EditSongTemplateScreen
import app.screens.single_song_template.SingleSongTemplateScreen
import app.style.appBg
import app.widgets.SearchView
import app.widgets.TemplateShimmerAnimation
import domain.model.SongTemplate
import kotlinx.coroutines.launch

@Composable
fun TemplateScreen() {
    Column(modifier = Modifier.background(Color.Transparent)) {
        val showSingleSongTemplate = remember { mutableStateOf(false) }
        val showIsEditSongTemplate = remember { mutableStateOf(false) }
        MainkContent(showSingleSongTemplate, showIsEditSongTemplate)
    }
}


@Composable
private fun MainkContent(
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
                favorite = false,
                glorifyingSong = emptyList(),
                worshipSong = emptyList(),
                giftSong = emptyList()
            )
        )
    }

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
            SearchView {}
            if (allSongs.value.isEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    items(15) {
                        TemplateShimmerAnimation()
                    }
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg)) {
                    items(allSongs.value) { menuItem ->
                        Column(modifier = Modifier.clickable {
                            selectedTemplateItem.value = menuItem
                            isShowSingleSongTemplate.value = !isShowSingleSongTemplate.value
                        }) {
                            SongTemplateColumItem(menuItem, showIsEditSongTemplate)
                        }
                    }
                }
            }
        }
    }
}