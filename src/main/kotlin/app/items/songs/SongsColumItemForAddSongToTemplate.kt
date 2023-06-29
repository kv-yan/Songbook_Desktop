package app.items.songs

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.extensions.song.getWordsFirst2Lines
import domain.model.Song

@Preview
@Composable
fun SongsColumItemForAddSongToTemplate(
    song: Song, actionClick: (song: Song) -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
        actionClick(song)
    }) {
        Image(
            painter = painterResource(resourcePath = "ic_music.png"),
            contentDescription = "Search", modifier = Modifier/*.size(24.dp)*/.padding(start = 8.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth(0.96f)
        ) {
            Text(
                text = song.title,
                style = MaterialTheme.typography.body2,
                color = Color.White, modifier = Modifier.padding(bottom = 4.dp),
            )
            Text(
                text = song.getWordsFirst2Lines(),
                fontSize = 12.sp,
                color = Color.White
            )
            Spacer(Modifier.weight(1f))
        }

        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Search",
            tint = Color.DarkGray, modifier = Modifier.padding(3.dp).size(24.dp)
        )

    }
}
