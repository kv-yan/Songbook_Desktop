package app.screens.single_song

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import app.dialog.song.IsDeleteSongDialog
import app.style.appBg
import app.style.appTextColor
import app.widgets.MenuArrowBack
import domain.model.Song

@Composable
fun SingleSongScreen(
    song: Song,
    isShowSingleSong: MutableState<Boolean>,
    onEditClick: () -> Unit,
) {
    val scrollState = rememberScrollState(0)
    val isShowDeleteSongDialog = remember { mutableStateOf(false) }
    SelectionContainer {
        Column(modifier = Modifier.background(appBg).fillMaxSize().verticalScroll(scrollState)) {
            Surface(
                modifier = Modifier.fillMaxWidth().height(65.dp).padding(bottom = 15.dp), color = Color(0xFF2A2A2A)
            ) {
                Row(modifier = Modifier.padding(end = 45.dp), verticalAlignment = Alignment.CenterVertically) {
                    MenuArrowBack(isShowSingleSong)
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                        IconButton(onClick = { onEditClick.invoke() }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = appTextColor)
                        }

                        IconButton(onClick = { isShowDeleteSongDialog.value = true }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = appTextColor)
                        }
                    }
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()/*.padding(vertical = 16.dp, horizontal = 16.dp)*/
            ) {

                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle()) {
                            append(song.title)
                        }
                    },
                    style = MaterialTheme.typography.body1.copy(),
                    color = appTextColor,
                    modifier = Modifier.fillMaxWidth(0.89f),
                )
                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        append(song.tonality)
                    }
                }, style = MaterialTheme.typography.body1, color = appTextColor)
                Text(text = " | ${song.temp}", style = MaterialTheme.typography.body1, color = appTextColor)
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = song.words,
                style = MaterialTheme.typography.body1,
                color = appTextColor,
                modifier = Modifier.fillMaxSize().padding(start = 26.dp, top = 26.dp, bottom = 40.dp)
            )
        }
    }
    if (isShowDeleteSongDialog.value) {
        IsDeleteSongDialog(song, isShowDeleteSongDialog)
    }
}
