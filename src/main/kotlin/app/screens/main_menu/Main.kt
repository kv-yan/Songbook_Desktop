package app.screens.main_menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import app.additional.initFirebase
import app.items.menu.MenuItemRow
import app.screens.add_new_song.NewSongScreen
import app.screens.add_new_template.NewTemplateScreen
import app.screens.home.HomeScreen
import app.screens.template.TemplateScreen
import app.style.appBg
import app.style.appSecondaryColor
import domain.*
import domain.model.MenuItem

@Composable
fun MainScreen() {
    remember {
        initFirebase()
    }

    /*   ******** UI ********   */
    val menuItems = getMenuItems()
    val selectedItem = remember { mutableStateOf(menuItems[0]) }

    Row(modifier = Modifier.fillMaxSize().background(appBg)) {
        MenuSection(menuItems = menuItems,
            selectedItem = selectedItem.value,
            navigationDestination = selectedItem.value,
            onMenuItemClick = { menuItem ->
                selectedItem.value = menuItem
            })
        ScreenSection(selectedItem.value)
    }

}

@Composable
fun MenuSection(
    menuItems: List<MenuItem>,
    selectedItem: MenuItem,
    navigationDestination: MenuItem,
    onMenuItemClick: (MenuItem) -> Unit,
) {
    Column(modifier = Modifier.width(220.dp).drawBehind {
        val x = size.width
        val y = size.height
        drawLine(color = appSecondaryColor, start = Offset(x, 0f), end = Offset(x, y), strokeWidth = 0.5f)
    }) {
        Image(
            painter = painterResource("app_logo_transparent.png"),
            contentDescription = "Some description",
            modifier = Modifier.width(240.dp).height(120.dp).padding(end = 150.dp, top = 16.dp)
        )
        Surface(modifier = Modifier.width(200.dp)) {
            LazyColumn(modifier = Modifier.fillMaxHeight().background(appBg)) {
                items(menuItems) { menuItem ->
                    MenuItemRow(
                        menuItem = menuItem, onItemClick = {
                            onMenuItemClick(menuItem)
                            navigationDestination.destination = menuItem.destination
                        }, menuItem == selectedItem
                    )
                }
            }
        }
    }
}


@Composable
fun ScreenSection(selectedItem: MenuItem) {
    when (selectedItem.title) {
        HOME_TITLE -> HomeScreen()
        TEMPLATE_TITLE -> TemplateScreen()
        NEW_SONG_TITLE -> NewSongScreen()
        NEW_TEMPLATE_TITLE -> NewTemplateScreen()
//        SETTINGS_TITLE -> SettingsScreen()
    }
}


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(),
        icon = painterResource("app_logo_transparent.png"),
        title = "Բեթհել Երգացուցակ",
    ) {
        MainScreen()
    }
}


