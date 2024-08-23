package app.screens.main_menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
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
import org.apache.poi.xwpf.usermodel.XWPFDocument
import java.awt.datatransfer.DataFlavor
import java.awt.dnd.DropTarget
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.swing.JOptionPane

@Composable
fun MainScreen(newSongTitle: MutableState<TextFieldValue>, newSongWords: MutableState<TextFieldValue>) {
    remember {
        initFirebase()
    }

    /*   ******** UI ********   */
    val menuItems = getMenuItems(newSongTitle, newSongWords)
    val selectedItem = remember { mutableStateOf(menuItems[1]) }

    Row(modifier = Modifier.fillMaxSize().background(appBg)) {
        MenuSection(

            menuItems = menuItems,
            selectedItem = selectedItem.value,
            navigationDestination = selectedItem.value,
            onMenuItemClick = { menuItem ->
                selectedItem.value = menuItem
            })
        ScreenSection(
            selectedItem.value, newSongWords = newSongWords,
            newSongTitle = newSongTitle,
        )
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
fun ScreenSection(
    selectedItem: MenuItem,
    newSongTitle: MutableState<TextFieldValue>, newSongWords: MutableState<TextFieldValue>,
) {
    when (selectedItem.title) {
        HOME_TITLE -> HomeScreen()
        TEMPLATE_TITLE -> TemplateScreen()
        NEW_SONG_TITLE -> NewSongScreen(newSongTitle, newSongWords)
        NEW_TEMPLATE_TITLE -> NewTemplateScreen()
    }
}


fun main() = singleWindowApplication {
    val titleState = remember { mutableStateOf(TextFieldValue()) }
    val wordsState = remember { mutableStateOf(TextFieldValue()) }

    // Set up the drop target
    LaunchedEffect(Unit) {
        window.dropTarget = object : DropTarget() {
            override fun drop(evt: java.awt.dnd.DropTargetDropEvent) {
                try {
                    evt.acceptDrop(java.awt.dnd.DnDConstants.ACTION_COPY)
                    val droppedFiles = evt.transferable.getTransferData(DataFlavor.javaFileListFlavor) as List<*>
                    val file = droppedFiles[0] as File

                    if (file.extension == "docx") {
                        val (parsedTitle, parsedText) = parseDocxFile(file.absolutePath)

                        // Check if parsedTitle and parsedText are not empty before updating
                        if (parsedTitle.isNotEmpty()) {
                            titleState.value = titleState.value.copy(text = parsedTitle)
                        }
                        if (parsedText.isNotEmpty()) {
                            wordsState.value = wordsState.value.copy(text = parsedText)
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a .docx file.")
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

    // Main Screen with Drop functionality
    MainScreen(titleState, wordsState)
}

fun parseDocxFile(filePath: String): Pair<String, String> {
    val inputStream: InputStream = FileInputStream(filePath)
    val document = XWPFDocument(inputStream)

    val paragraphs = document.paragraphs

    val title = paragraphs.first().text
    val text = paragraphs.drop(1).joinToString("\n") { it.text }

    inputStream.close()
    return Pair(title, text)
}
