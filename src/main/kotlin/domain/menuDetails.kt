package domain

import androidx.compose.runtime.Composable
import app.screens.add_new_song.NewSongScreen
import app.screens.add_new_template.NewTemplateScreen
import app.screens.home.HomeScreen
import app.screens.template.TemplateScreen
import domain.model.MenuItem

@Composable
fun getMenuItems(): List<MenuItem> {
    return listOf(
        MenuItem(HOME_TITLE, "ic_home.png", action = HomeScreen(), destination = HOME_TITLE),
        MenuItem(TEMPLATE_TITLE, "ic_template.png", action = TemplateScreen(), destination = TEMPLATE_TITLE),
        MenuItem(NEW_SONG_TITLE, "add_new_song.png", action = NewSongScreen(), destination = NEW_SONG_TITLE),
        MenuItem(
            NEW_TEMPLATE_TITLE, "add_new_template.png", action = NewTemplateScreen(), destination = NEW_TEMPLATE_TITLE
        ),
    )
}