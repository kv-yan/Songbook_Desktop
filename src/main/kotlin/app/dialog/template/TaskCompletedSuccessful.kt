package app.dialog.template

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
                backgroundColor = Color.Green,
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
                        Text(
                            text = "Չեղարկել",
                            modifier = Modifier.clickable {
                                showDialog.value = false
                            }.padding(vertical = 16.dp, horizontal = 16.dp),
                            style = MaterialTheme.typography.body1,
                            color = appTextColor
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(0.4f).align(Alignment.Center)
            )
        }
    }
}