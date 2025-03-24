package app.dialog.song

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
import androidx.compose.ui.unit.dp
import app.style.appSecondaryColor
import app.style.appTextColor

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskCompletedSuccessful(showDialog: MutableState<Boolean>) {
    if (showDialog.value) {

        AlertDialog(
            modifier = Modifier.fillMaxWidth(0.4f),
            backgroundColor = appSecondaryColor,
            shape = RoundedCornerShape(12.dp),
            onDismissRequest = { showDialog.value = false },
            title = {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                    text = "Փոոխությունը հաջողությամբ պահպանվել է",
                    style = MaterialTheme.typography.subtitle1,
                    color = appTextColor
                )
            },

            buttons = {
/*
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showDialog.value = false }
                            .padding(vertical = 16.dp, horizontal = 24.dp),
                        style = MaterialTheme.typography.body1,
                        color = appTextColor,
                        textAlign = TextAlign.End,
                        text = "Չեղարկել"
                    )
*/
            },
        )
    }
}