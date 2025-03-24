package app.dialog.song

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.style.appTextColor
import domain.model.Song

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IsDeleteSongDialog(
    song: Song,
    showDialog: MutableState<Boolean>,
    onDeleteClick: (Song) -> Unit,
) {
    AlertDialog(
        backgroundColor = Color.Red.copy(alpha = 0.8f),
        shape = RoundedCornerShape(12.dp),
        onDismissRequest = { showDialog.value = false },
        title = {
            Text(
                text = "Իսկապես ցանկանում եք ջնջել երգը",
                style = MaterialTheme.typography.subtitle1,
                color = appTextColor
            )
        },
        text = {
            Text(text = "Իսկապես ցանկանում եք ջնջել \" ${song.title} \" - երգը ", color = appTextColor)
        },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(text = "Չեղարկել", modifier = Modifier.clickable {
                    showDialog.value = false
                }.padding(vertical = 16.dp), style = MaterialTheme.typography.body1, color = appTextColor)
                Text(
                    text = "Ջնջել",
                    modifier = Modifier.clickable {
                        onDeleteClick(song)
                    }.padding(horizontal = 12.dp, vertical = 16.dp),
                    style = MaterialTheme.typography.body2,
                    color = appTextColor
                )
            }
        },
        modifier = Modifier.fillMaxWidth(0.4f)
    )
}
