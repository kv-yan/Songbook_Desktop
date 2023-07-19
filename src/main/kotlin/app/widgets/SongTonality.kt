package app.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.extensions.song.getWordsFirst2Lines


@Composable
fun SongTonality(text: String) {

    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = Color.White, modifier = Modifier.padding(bottom = 4.dp),
    )}