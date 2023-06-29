package app.items.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import domain.model.MenuItem

@Composable
fun MenuItemRow(
    menuItem: MenuItem, onItemClick: (action: Unit) -> Unit, isSelected: Boolean
) {
    Row(
        modifier = Modifier.clickable { onItemClick(menuItem.action) }.fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp, start = 12.dp)
    ) {
        Image(
            painter = painterResource(resourcePath = menuItem.iconResId),
            contentDescription = "Icon",
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = menuItem.title, style = MaterialTheme.typography.body1, modifier = Modifier, color = Color.White
        )
    }

}
