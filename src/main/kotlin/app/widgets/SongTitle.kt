package app.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun SongTitle(text:String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        color = Color.White, modifier = Modifier.padding(bottom = 4.dp),
    )
}