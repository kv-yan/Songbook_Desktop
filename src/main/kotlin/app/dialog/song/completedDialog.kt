package app.dialog.song

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCompletedSuccessful(showDialog: MutableState<Boolean>) {
    if (showDialog.value) {
        Box(
            modifier = Modifier.background(Color.Red)
                .shadow(elevation = 5.dp, spotColor = Color.White, ambientColor = Color.Red)
        ) {
            AlertDialog(
                backgroundColor = /*appBg*/ Color.Green,
                shape = MaterialTheme.shapes.large,
                onDismissRequest = { showDialog.value = false },
                title = {
                    Text(
                        text = "Փոոխությունը հաջողությամբ պահպանվել է",
                        style = MaterialTheme.typography.subtitle1,
                        color = appTextColor, modifier = Modifier.fillMaxWidth().align(Alignment.Center)
                    )
                },

                buttons = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "Չեղարկել", modifier = Modifier.clickable {
                            showDialog.value = false
                        }.padding(vertical = 16.dp), style = MaterialTheme.typography.body1, color = appTextColor)
                    }
                },
                modifier = Modifier.fillMaxWidth(0.4f).align(Alignment.Center)
            )
        }
    }
}