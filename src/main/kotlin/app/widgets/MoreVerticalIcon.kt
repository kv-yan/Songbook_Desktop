package app.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.dialog.song.IsDeleteSongDialog
import app.dialog.template.IsDeleteSongTemplateDialog
import domain.model.Song
import domain.model.SongTemplate


@Composable
fun MoreVerticalIcon(song: Song, onEditClicked: (Song) -> Unit) {
    val items = remember {
        mutableStateListOf("Խմբագրել", "Ջնջել")
    }

    val expanded = remember { mutableStateOf(false) }
    val isShowDeleteSongDialog = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf(items.last()) }

    Icon(imageVector = Icons.Filled.MoreVert,
        contentDescription = "",
        tint = Color.DarkGray,
        modifier = Modifier.padding(8.dp).background(color = Color.White, shape = CircleShape).clickable {
            expanded.value = !expanded.value
        })


    if (expanded.value) {
        Column(Modifier) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedItem.value = item
                        expanded.value = false

                        when (item) {
                            "Խմբագրել" -> {
                                onEditClicked(song)
                            }

                            "Ջնջել" -> {
                                isShowDeleteSongDialog.value = true
                            }
                        }
                    }) {
                        Text(
                            text = item, modifier = Modifier.padding(8.dp), color = Color.Black
                        )
                    }
                }
            }
        }

    }
    if (isShowDeleteSongDialog.value) {
        IsDeleteSongDialog(song, isShowDeleteSongDialog)
    }
}

@Composable
fun MoreVerticalIconTemplate(
    template: SongTemplate,
    isShowEditTemplateScreen: MutableState<Boolean>,
) {
    val items = remember {
        mutableStateListOf("Խմբագրել", "Ջնջել")
    }

    val expanded = remember { mutableStateOf(false) }
    val isShowDeleteSongTemplateDialog = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf(items.last()) }

    Icon(imageVector = Icons.Filled.MoreVert,
        contentDescription = "",
        tint = Color.DarkGray,
        modifier = Modifier.padding(8.dp).background(color = Color.White, shape = CircleShape).clickable {
            expanded.value = !expanded.value
        })


    if (expanded.value) {
        Column(Modifier) {
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedItem.value = item
                        expanded.value = false

                        when (item) {
                            "Խմբագրել" -> {
                                isShowEditTemplateScreen.value = true
                            }

                            "Ջնջել" -> {
                                isShowDeleteSongTemplateDialog.value = true
                            }
                        }
                    }) {
                        Text(
                            text = item, modifier = Modifier.padding(8.dp), color = Color.Black
                        )
                    }
                }
            }
        }

    }
    if (isShowDeleteSongTemplateDialog.value) {
        IsDeleteSongTemplateDialog(template, isShowDeleteSongTemplateDialog)
    }
}