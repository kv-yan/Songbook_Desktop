package app.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import app.style.appSecondaryColor

@Composable
fun TextField(placeholder: String, textFieldValue: MutableState<TextFieldValue>) {
    TextField(
        value = textFieldValue.value,
        onValueChange = { it: TextFieldValue ->
            textFieldValue.value = it
        },
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = appSecondaryColor,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Gray,
            textColor = Color.White
        ), modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TextField(placeholder: String, textFieldValue: MutableState<TextFieldValue>, textStyle: TextStyle) {
    TextField(
        value = textFieldValue.value,
        onValueChange = { it: TextFieldValue -> textFieldValue.value = it },
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = appSecondaryColor,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Gray,
            textColor = Color.White
        ), modifier = Modifier.fillMaxWidth(), textStyle = textStyle
    )
}