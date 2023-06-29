package app.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.style.appSecondaryColor

@Composable
fun RadioButtonWithText(
    label: String,
    selectedValue: MutableState<Boolean>,
    unselectedValue: MutableState<Boolean>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            selectedValue.value = true
            unselectedValue.value = false
        }
    ) {
        RadioButton(
            selected = selectedValue.value,
            onClick = {
                selectedValue.value = true
                unselectedValue.value = false
            },
            colors = RadioButtonDefaults.colors(selectedColor = appSecondaryColor, unselectedColor = Color.Gray)
        )
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(), color = (if (selectedValue.value) appSecondaryColor else Color.Gray)
        )
    }

}
