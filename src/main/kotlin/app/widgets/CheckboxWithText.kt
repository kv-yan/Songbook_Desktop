package app.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import app.style.appSecondaryColor

@Composable
fun CheckboxWithText(
    label: String, selectedValue: MutableState<Boolean>
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
        run {
            selectedValue.value = !selectedValue.value
        }
    }) {
        Checkbox(
            checked = selectedValue.value,
            onCheckedChange = { selectedValue.value = it },
            colors = CheckboxDefaults.colors(checkedColor = appSecondaryColor, uncheckedColor = Color.Gray)
        )
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth(),
            color = (if (selectedValue.value) appSecondaryColor else Color.Gray)
        )
    }

}