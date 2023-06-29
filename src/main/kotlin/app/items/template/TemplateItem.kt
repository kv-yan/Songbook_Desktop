package app.items.template

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.widgets.MoreVerticalIconTemplate
import domain.extensions.template.getDetails
import domain.model.SongTemplate

@Composable
fun SongTemplateColumItem(
    template: SongTemplate,
    isShowEditTemplateScreen: MutableState<Boolean>,
) {
    val isShowingTemplateDetails = remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp).fillMaxWidth(0.94f)
        ) {

            Text(
                text = template.performerName,
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.fillMaxSize(0.6f)
            )

            Row(modifier = Modifier.fillMaxSize(0.5f), horizontalArrangement = Arrangement.Start) {
                Text(
                    text = template.weekday,
                    style = MaterialTheme.typography.body1,
                    color = Color.White,
                )

                Text(
                    text = "| ${template.createDate}", style = MaterialTheme.typography.body1, color = Color.White
                )

                Icon(painter = if (isShowingTemplateDetails.value) painterResource("ic_open_template.png")
                else painterResource("ic_close_template.png"),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(start = 16.dp).clickable {
                        isShowingTemplateDetails.value = !isShowingTemplateDetails.value
                    })
            }

            MoreVerticalIconTemplate(
                template, isShowEditTemplateScreen
            )
        }

        AnimatedVisibility(visible = isShowingTemplateDetails.value) {
            Text(
                text = template.getDetails(),
                style = MaterialTheme.typography.body1,
                color = Color.White,
                modifier = Modifier.padding(start = 24.dp, bottom = 24.dp)
            )
        }
    }
}
