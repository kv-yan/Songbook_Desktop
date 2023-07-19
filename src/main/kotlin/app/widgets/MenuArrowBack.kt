package app.widgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

@Composable
fun MenuArrowBack(isShowContent: MutableState<Boolean>) {
    IconButton(onClick = { isShowContent.value = !isShowContent.value }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null, tint = Color.White)
    }
}