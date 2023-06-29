package app.dialog.template

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.style.appTextColor
import data.lambda.template.delete.onDeleteSongTemplateItem
import domain.model.SongTemplate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IsDeleteSongTemplateDialog(template: SongTemplate, showDialog: MutableState<Boolean>) {
    if (showDialog.value) {
        Box(
            modifier = Modifier.background(Color.Red)
                .shadow(elevation = 5.dp, spotColor = Color.White, ambientColor = Color.Red)
        ) {
            AlertDialog(
                backgroundColor = /*appBg*/ Color.Red,
                shape = MaterialTheme.shapes.large,
                onDismissRequest = { showDialog.value = false },
                title = {
                    Text(
                        text = "Իսկապես ցանկանում եք ջնջել ցուցակը ?",
                        style = MaterialTheme.typography.subtitle1,
                        color = appTextColor
                    )
                },
                buttons = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "Չեղարկել", modifier = Modifier.clickable {
                            showDialog.value = false
                        }.padding(vertical = 16.dp), style = MaterialTheme.typography.body1, color = appTextColor)
                        Text(
                            text = "Ջնջել",
                            modifier = Modifier.clickable {
                                showDialog.value = false
                                onDeleteSongTemplateItem(template)
                            }.padding(horizontal = 12.dp, vertical = 16.dp),
                            style = MaterialTheme.typography.body2, color = appTextColor
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(0.4f).align(Alignment.Center)
            )
        }
    }
}