package app.screens.single_song

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import app.style.appBg
import app.style.appTextColor
import app.widgets.MenuArrowBack
import domain.model.Song

@Composable
fun SingleSongScreen(
    song: Song,
    isShowSingleSong: MutableState<Boolean>,
) {
    SelectionContainer {
        Column(modifier = Modifier.background(appBg).fillMaxSize().padding(vertical = 16.dp, horizontal = 16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                MenuArrowBack(isShowSingleSong)
                Spacer(modifier = Modifier.width(18.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle()) {
                            append(song.title)
                        }
                    },
                    style = MaterialTheme.typography.body1.copy(),
                    color = appTextColor,
                    modifier = Modifier.fillMaxWidth(0.89f).padding(start = 3.dp),
                )
                Text(text = buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        append(song.tonality)
                    }
                }, style = MaterialTheme.typography.body1, color = appTextColor)
                Text(text = " | song.temp", style = MaterialTheme.typography.body1, color = appTextColor)
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = song.words,
                style = MaterialTheme.typography.body1,
                color = appTextColor,
                modifier = Modifier.fillMaxSize().padding(top = 26.dp)
            )
        }
    }
}
