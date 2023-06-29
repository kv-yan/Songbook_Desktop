package app.items.songs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.widgets.MoreVerticalIcon
import app.widgets.MusicSongIcon
import app.widgets.SongTitle
import app.widgets.SongTonality
import domain.extensions.song.getWordsFirst2Lines
import domain.model.Song

@Composable
fun SongsColumItem(song: Song, clickedItem: MutableState<Song>, onEditingItem: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        MusicSongIcon()
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth(0.96f)) {
            SongTitle(song.title)
            SongTonality(song.getWordsFirst2Lines())
            Spacer(Modifier.weight(1f))
        }
        MoreVerticalIcon(song = song, onEditClicked = {
            clickedItem.value = song
            onEditingItem()
        })
    }
}


@Composable
fun SongsColumItem(song: Song, selectedItem: MutableState<Boolean>) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
        selectedItem.value = !selectedItem.value
    }) {
        MusicSongIcon()
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth(0.96f)) {
            SongTitle(song.title)
            SongTonality(song.getWordsFirst2Lines())
            Spacer(Modifier.weight(1f))
        }
    }
}