package app.screens.add_new_song

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberDialogState
import app.style.appBg
import app.style.appSecondaryColor
import app.style.appTextColor
import domain.model.Song
import kotlinx.coroutines.delay
import java.awt.Toolkit

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SongMatchedDialog(
    modifier: Modifier = Modifier,
    isShowingDialog: Boolean,
    matchedSongs: List<Song> = emptyList(),
    onSaveClick: () -> Unit = {},
    onCancelClick: () -> Unit = {},
) {
    val density = LocalDensity.current
    val screenSize = remember { Toolkit.getDefaultToolkit().screenSize }

    val dialogWidth = with(density) { (screenSize.width * 0.7f).toDp() }
    val dialogHeight = with(density) { (screenSize.height).toDp() }

    val dialogState = rememberDialogState(
        position = WindowPosition(Alignment.Center), size = DpSize(dialogWidth, dialogHeight)
    )

    val verticalScroll = rememberScrollState()

    if (isShowingDialog) {

        Column(
            modifier = modifier.fillMaxSize()
                .background(appBg, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
                .verticalScroll(verticalScroll)
        ) {

            Text(
                text = "Կա համընկած երգ, խնդրում ենք համոզվել ավելացնոլուց առաջ !",
                style = MaterialTheme.typography.h6,
                color = Color.Red,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            HorizontalPager(
                modifier = Modifier.fillMaxSize(), pageCount = matchedSongs.size
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = matchedSongs[it].title,
                        style = MaterialTheme.typography.h5,
                        color = appTextColor
                    )

                    Text(
                        color = appTextColor, text = matchedSongs[it].words, style = MaterialTheme.typography.body2
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.LightGray
                ), onClick = {
                    onCancelClick()
                }) {
                    Text("Չեղարկել")
                }
                Button(modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors(
                    backgroundColor = appSecondaryColor, contentColor = Color.White
                ), onClick = {
                    onSaveClick()
                }) {
                    Text("Ավելացնել")
                }
            }
        }
    }
}


fun main() = application {
    val showing = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(1000)
        showing.value = true
    }
    Window(onCloseRequest = ::exitApplication) {
        SongMatchedDialog(isShowingDialog = showing.value, onCancelClick = {
            showing.value = false
        }, onSaveClick = {
            showing.value = false
        })
    }
}
