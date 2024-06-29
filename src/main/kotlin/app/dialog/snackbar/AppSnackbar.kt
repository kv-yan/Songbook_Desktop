package app.dialog.snackbar

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppSnackbar(isShowing: MutableState<Boolean>, modifier: Modifier, content: @Composable () -> Unit) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isShowing.value,
        enter = fadeIn() + slideInVertically(initialOffsetY = { -180 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -50 }),
        initiallyVisible = false
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            content.invoke()
        }
    }

    LaunchedEffect(isShowing.value) {
        if (isShowing.value) {
            delay(4000)
            isShowing.value = false
        }
    }
}

@Composable
fun MainContent() {
    val showSnackbar = remember { mutableStateOf(false) }
    val message = "Do something"

    Box(
        modifier = Modifier.fillMaxSize()
    ) {


        AppSnackbar(isShowing = showSnackbar, Modifier) {

        }

        Button(onClick = { showSnackbar.value = true }) {
            Text("Show Snackbar")
        }

    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainContent()
    }
}
