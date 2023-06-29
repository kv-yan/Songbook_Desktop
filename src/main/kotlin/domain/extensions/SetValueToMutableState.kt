package domain.extensions

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import domain.model.Song

fun MutableState<SnapshotStateList<Song>>.addValue(data: List<Song>) {
    data.forEach { item ->
        this.value.add(item)
    }
}