package app.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.style.appSecondaryColor

@Composable
fun EmptyCategoryText() {
    val stroke = Stroke(
        width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
    )
    Row(modifier = Modifier.padding(16.dp)) {
        Box(
            Modifier.fillMaxWidth().height(100.dp).drawBehind {
                drawRoundRect(color = appSecondaryColor, style = stroke)
            }, contentAlignment = Alignment.Center
        ) {
            Row {
                Text(textAlign = TextAlign.Center, text = "Դատարկ դաշտ Է ", color = appSecondaryColor)
                Text(
                    textAlign = TextAlign.Center, text = " Ավելացնել երգ", color = Color.Gray
                )
            }
        }
    }
}