package test

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.items.songs.SongsColumItem
import app.widgets.*
import domain.extensions.song.getWordsFirst2Lines
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

data class DragAndDropItem(val id: Int, val text: String)


@OptIn(ExperimentalFoundationApi::class)
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {

        val data = remember { mutableStateOf(List(100) { "Item $it" }) }
        val state = rememberReorderableLazyListState(onMove = { from, to ->
            data.value = data.value.toMutableList().apply {
                add(to.index, removeAt(from.index))
            }
        })
        LazyColumn(
            state = state.listState,
            modifier = Modifier
                .reorderable(state)
                .detectReorderAfterLongPress(state)
        ) {
            items(data.value, { it }) { item ->
                ReorderableItem(state, key = item) { isDragging ->
                    val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
                    Column(
                        modifier = Modifier
                            .shadow(elevation.value)
                            .background(MaterialTheme.colors.surface)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            MusicSongIcon()
                            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth(0.96f)) {
                                SongTitle("Մի՛ վախենար երբեք")
                                SongTonality("Մի՛ վախենար երբեք,Տե՛ս, քեզ հետ եմ Ես»,\nՍա է Իմ ճրագը.Մինչ ճամբորդ եմ ես։")
                                Spacer(Modifier.weight(1f))
                            }

                        }
                    }
                }
            }
        }
    }
}


