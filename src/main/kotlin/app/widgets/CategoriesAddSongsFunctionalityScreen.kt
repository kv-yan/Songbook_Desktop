package app.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.items.songs.SongsColumItemForAddSongToTemplate
import domain.model.Song

@Composable
fun CategoriesAddSongsFunctionalityScreen(
    isShowAllCategoriesSongs: MutableState<Boolean>,
    allCategorySongs: List<Song>,
    addSongTitle: MutableState<String>,
    actionClick: MutableState<(item: Song) -> Unit>
) {
    val searchText = remember { mutableStateOf(TextFieldValue()) }
    Column {
        Text(
            text = "Ավելացրեք ${addSongTitle.value}",
            style = MaterialTheme.typography.subtitle1,
            color = Color.Gray,
            modifier = Modifier.padding(16.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            MenuArrowBack(isShowAllCategoriesSongs)
            SearchView(searchText) { }
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(items = allCategorySongs) { index, items ->
                SongsColumItemForAddSongToTemplate(items) {
                    actionClick.value(items)
                }
            }
        }
    }
}
