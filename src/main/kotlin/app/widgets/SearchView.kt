package app.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.style.appSecondaryColor
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchView(searchQuery: MutableState<String>, onSearch: (text: String) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val debounceJob = remember { mutableStateOf<Job?>(null) }

    Row(modifier = Modifier.padding(8.dp)) {
        TextField(
            value = searchQuery.value,
            onValueChange = { newQuery ->
                searchQuery.value = newQuery

                // Отменяем предыдущий запрос
                debounceJob.value?.cancel()

                // Запускаем новый с задержкой
                debounceJob.value = coroutineScope.launch {
                    delay(300) // Ждём 300 мс после последнего ввода
                    onSearch(searchQuery.value)
                }
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
            }
        )
    }
}


