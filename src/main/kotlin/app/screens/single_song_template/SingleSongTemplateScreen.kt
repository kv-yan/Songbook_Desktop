package app.screens.single_song_template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.items.songs.SongsColumItem
import app.screens.single_song.SingleSongScreen
import app.style.appBg
import app.style.appTextColor
import app.widgets.MenuArrowBack
import domain.model.Song
import domain.model.SongTemplate

@Composable
fun SingleSongTemplateScreen(
    songTemplate: SongTemplate, isShowTemplateDetails: MutableState<Boolean>, isShowSingleSong: MutableState<Boolean>
) {
    if (isShowSingleSong.value) {
        SingleSongScreen(
            song = Song(
                id = "404",
                title = "404",
                tonality = "404",
                words = "404",
                isGlorifyingSong = false,
                isWorshipSong = false,
                isGiftSong = false,
                isFromSongbookSong = false
            ), isShowSingleSong = isShowSingleSong
        )
    } else {
        MainContent(songTemplate, isShowTemplateDetails, isShowSingleSong)
    }
}


@Composable
private fun MainContent(
    template: SongTemplate, isShowTemplateDetails: MutableState<Boolean>, isShowSingleSong: MutableState<Boolean>
) {
    Column(modifier = Modifier.background(appBg).fillMaxWidth().padding(vertical = 16.dp, horizontal = 16.dp)) {
        // Header content
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Back button
            MenuArrowBack(isShowTemplateDetails)
            Text(
                text = template.performerName,
                style = MaterialTheme.typography.subtitle1,
                color = appTextColor,
                modifier = Modifier.weight(0.85f).padding(start = 3.dp),
            )
            Text(text = template.weekday, style = MaterialTheme.typography.body1, color = appTextColor)
            Text(text = " | ${template.createDate}", style = MaterialTheme.typography.body1, color = appTextColor)
        }



        Spacer(Modifier.height(15.dp))
        Text(
            text = "Փառաբանություն",
            style = MaterialTheme.typography.body1,
            color = appTextColor,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg).weight(1f)) {
            items(template.glorifyingSong) { item ->
                SongsColumItem(item, isShowSingleSong)

            }
        }
        Text(
            text = "Երկրպագություն",
            style = MaterialTheme.typography.body1,
            color = appTextColor,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg).weight(1f)) {
            items(template.worshipSong) { item ->
                SongsColumItem(item, isShowSingleSong)
            }
        }
        Text(
            text = "Ընծա",
            style = MaterialTheme.typography.body1,
            color = appTextColor,
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
        )

        LazyColumn(modifier = Modifier.fillMaxWidth().background(appBg).weight(1f)) {
            items(template.giftSong) { item ->
                SongsColumItem(item, isShowSingleSong)
            }
        }
    }

}