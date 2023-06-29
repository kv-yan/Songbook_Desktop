package app.screens.edit_song_template

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import app.style.appBg
import app.style.appSecondaryColor
import app.widgets.CheckboxWithText
import app.widgets.TextField
import app.widgets.WeekdayDropDownMenu
import data.lambda.template.update.onUpdateSongTemplateItem
import domain.model.SongTemplate

@Composable
fun TemplateSettingsScreenForEdit(
    isShowTemplateSettings: MutableState<Boolean>,
    performerTextFieldValue: MutableState<TextFieldValue>,
    templateWeekday: MutableState<String>,
    isSendNotification: MutableState<Boolean>,
    template: SongTemplate,
) {
    AnimatedVisibility(visible = isShowTemplateSettings.value) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TextField("Հեղինակ․․․", performerTextFieldValue)

            Column {
                WeekdayDropDownMenu(templateWeekday)
                CheckboxWithText("Ուղարկել ծանուցում բոլորին", isSendNotification)
                Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.background(appBg).fillMaxSize()) {
                    Button(
                        onClick = {
                            onUpdateSongTemplateItem(template)
                        },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = appSecondaryColor)
                    ) {
                        Text(
                            text = "Պահպանել",
                            style = MaterialTheme.typography.subtitle1,
                        )
                    }
                }
            }
        }
    }
}
