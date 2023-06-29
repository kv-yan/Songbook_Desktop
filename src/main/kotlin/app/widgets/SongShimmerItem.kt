package app.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp


@Composable
fun SongShimmerItem(brush: Brush) {
    Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
        Spacer(
            modifier = Modifier.fillMaxWidth().height(35.dp)
                .background(brush = brush, shape = RoundedCornerShape(15), alpha = 0.5f)
        )
        Spacer(
            modifier = Modifier.fillMaxWidth().height(35.dp).padding(vertical = 8.dp)
                .background(brush = brush, shape = RoundedCornerShape(15))
        )
    }
}