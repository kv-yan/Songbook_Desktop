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
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.style.appSecondaryColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchView(onSearch: (text: String) -> Unit) {
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }

    Row(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                onSearch(searchQuery.text)
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
            keyboardActions = KeyboardActions(onAny = { onSearch(searchQuery.text) })
        )
    }
}


