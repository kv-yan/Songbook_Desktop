package app.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.style.appBg
import app.style.appSecondaryColor


@Composable
fun TemplateShimmerItem(brush: Brush) {
    Column(modifier = Modifier.fillMaxWidth().background(brush = brush).padding(5.dp)) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier.fillMaxWidth(0.7f).height(35.dp).padding(vertical = 8.dp, horizontal = 16.dp)
                    .background(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(15))
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 30.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier.fillMaxWidth(0.4f).height(35.dp).padding(vertical = 8.dp, horizontal = 16.dp)
                        .background(color = Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(15))
                )

                Spacer(
                    modifier = Modifier.size(24.dp)
                        .background(color = Color.White.copy(alpha = 0.2f), shape = CircleShape)
                )
            }

        }


        Spacer(
            modifier = Modifier.fillMaxWidth().height(135.dp).padding(vertical = 8.dp, horizontal = 16.dp)
                .background(color = Color.White.copy(alpha = 0.1f), shape = RoundedCornerShape(15))
        )
    }
}