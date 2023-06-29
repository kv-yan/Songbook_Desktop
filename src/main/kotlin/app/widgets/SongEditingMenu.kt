package app.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import app.style.appBg
import app.style.appTextColor

@Composable
fun SongEditingMenu(expanded:MutableState<Boolean>) {
    val items = remember {
        mutableStateListOf("խմբագրել", "Ջնջել")
    }

    val selectedItem = remember { mutableStateOf(items.last()) }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 12.dp)
        .clickable { expanded.value = true }) {
        Text(text = selectedItem.value,
            style = MaterialTheme.typography.body1,
            color = appTextColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp).clickable { expanded.value = true })

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.background(appBg)
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedItem.value = item
                    expanded.value = false
                }) {
                    Text(
                        text = item, style = TextStyle.Default,
                        color = appTextColor,
                    )
                }
            }
        }
    }
}