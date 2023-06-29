package app.widgets

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import app.style.ShimmerColorShades

@Composable
fun SongShimmerAnimation(
) {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 50f,
        targetValue = 1800f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1300, easing = FastOutSlowInEasing),
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    SongShimmerItem(brush = brush)
}
