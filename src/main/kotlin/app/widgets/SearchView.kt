package app.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.style.appSecondaryColor

@Composable
fun SearchView(searchQuery: MutableState<TextFieldValue>, onSearch: (text: String) -> Unit) {

    Row(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = searchQuery.value,
            onValueChange = {
                searchQuery.value = it
                onSearch(searchQuery.value.text)
            },
            placeholder = { Text(text = "Որոնել...", color = Color.Gray) },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = appSecondaryColor,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray,
                textColor = Color.White
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = "Search", tint = Color.White
                )
            },
            keyboardActions = KeyboardActions(onAny = { onSearch(searchQuery.value.text) })
        )
    }
}


