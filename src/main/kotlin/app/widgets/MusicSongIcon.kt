package app.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MusicSongIcon() {
    Image(
        painter = painterResource(resourcePath = "ic_music.png"),
        contentDescription = "Song Item",
        modifier = Modifier.padding(start = 16.dp)
    )
}