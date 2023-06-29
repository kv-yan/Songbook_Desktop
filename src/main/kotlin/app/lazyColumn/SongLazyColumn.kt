package app.lazyColumn

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import app.items.songs.SongItemWithDeleteBtn
import app.style.appBg
import domain.model.Song
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@Composable
fun SongLazyColumn(
    songList: MutableState<SnapshotStateList<Song>>,
    onDeleteItem: (Song) -> Unit
) {
    val data = remember { mutableStateOf(songList.value) }
    val state = rememberReorderableLazyListState(onMove = { from, to ->
        data.value = data.value.toMutableList().apply {
            add(to.index, removeAt(from.index))
        }.toSnapshotStateList()
    })


    LazyColumn(
        state = state.listState,
        modifier = Modifier.reorderable(state).detectReorderAfterLongPress(state).fillMaxWidth().background(appBg)
    ) {
        items(songList.value, { it }) { item ->
            ReorderableItem(state, key = item) { isDragging ->
                val elevation = animateDpAsState(if (isDragging) 16.dp else 0.dp)
                Column(
                    modifier = Modifier.shadow(elevation.value)
                ) {
                    SongItemWithDeleteBtn(item, onDeleteItem = { onDeleteItem(item) })
                }
            }
        }
    }
}


fun MutableList<Song>.toSnapshotStateList(): SnapshotStateList<Song> {
    val list = mutableStateListOf<Song>()
    this.forEach {
        list.add(it)
    }
    println("worked")

    return list
}