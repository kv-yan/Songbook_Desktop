package app.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.items.songs.SongItemWithDeleteBtn
import app.lazyColumn.SongLazyColumn
import data.lambda.song.delete.onDeleteSongItem
import domain.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CategorySongLabel(
    placeholder: String,
    categorySongsList: MutableState<SnapshotStateList<Song>>,
    isShowAllCategoriesSongs: MutableState<Boolean>,
    addSongTitle: MutableState<String>,
    actionClick: MutableState<(item: Song) -> Unit>,
) {
    Column {
        val scope = rememberCoroutineScope()
        Text(
            text = placeholder,
            modifier = Modifier.fillMaxWidth(0.94f),
            style = MaterialTheme.typography.subtitle1,
            color = Color.Gray
        )
        Spacer(Modifier.height(9.dp))
        if (categorySongsList.value.isEmpty()) {
            Box(modifier = Modifier.clickable {
                if (isShowAllCategoriesSongs.value) {
                    scope.launch {
                        isShowAllCategoriesSongs.value = false
                        delay(200)

                        isShowAllCategoriesSongs.value = !isShowAllCategoriesSongs.value
                    }
                } else {
                    isShowAllCategoriesSongs.value = !isShowAllCategoriesSongs.value
                }
                addSongTitle.value = placeholder
                actionClick.value = { item ->
                    categorySongsList.value.add(item)
                }
            }) {
                EmptyCategoryText()
            }
        } else {
//            LazyColumn {
//                itemsIndexed(items = categorySongsList) { index, items ->
//                    SongItemWithDeleteBtn(song = categorySongsList[index],
//
//                        onDeleteItem = { _ ->
//                            categorySongsList.remove(items)
//                            println(categorySongsList.size)
//                        })
//                }
//            }

            SongLazyColumn(categorySongsList) { item: Song ->
                onDeleteSongItem(item)
            }
        }
        Text(
            text = "Ավելացնել",
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.fillMaxWidth().clickable {
                if (isShowAllCategoriesSongs.value) {
                    scope.launch {
                        isShowAllCategoriesSongs.value = false
                        delay(300)

                        isShowAllCategoriesSongs.value = !isShowAllCategoriesSongs.value
                    }
                } else {
                    isShowAllCategoriesSongs.value = !isShowAllCategoriesSongs.value
                }

                actionClick.value = { item ->
                    categorySongsList.value.add(item)
                    addSongTitle.value = placeholder
                }
            }.padding(vertical = 16.dp, horizontal = 16.dp),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(25.dp))
    }
}
