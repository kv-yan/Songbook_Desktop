package domain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue
import app.screens.add_new_song.NewSongScreen
import app.screens.add_new_template.NewTemplateScreen
import app.screens.home.HomeScreen
import app.screens.template.TemplateScreen
import domain.model.MenuItem

@Composable
fun getMenuItems(newSongTitle: MutableState<TextFieldValue>, newSongWords: MutableState<TextFieldValue>): List<MenuItem> {
    return listOf(
        MenuItem(HOME_TITLE, "ic_home.png", action = HomeScreen(), destination = HOME_TITLE),
        MenuItem(TEMPLATE_TITLE, "ic_template.png", action = TemplateScreen(), destination = TEMPLATE_TITLE),
        MenuItem(NEW_SONG_TITLE, "add_new_song.png", action = NewSongScreen(newSongTitle, newSongWords), destination = NEW_SONG_TITLE),
        MenuItem(
            NEW_TEMPLATE_TITLE, "add_new_template.png", action = NewTemplateScreen(), destination = NEW_TEMPLATE_TITLE
        ),
    )
}