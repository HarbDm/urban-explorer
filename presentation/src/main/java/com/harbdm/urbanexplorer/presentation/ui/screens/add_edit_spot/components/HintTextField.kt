package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * TextField that provides hint before text entered.
 * Has a functionality to be single line or multiline.
 *
 * Note:
 * - Provide height with [modifier] if planning to have multiple lines
 */
@Composable
fun HintTextField(
    text: String,
    @StringRes hint: Int,
    @StringRes label: Int,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    isSingleLine: Boolean = true,
    textStyle: TextStyle = TextStyle()
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        textStyle = textStyle,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(8.dp),
        label = {
            Text(text = stringResource(label), style = textStyle, color = Color.DarkGray)
        },
        placeholder = {
            Text(text = stringResource(hint), style = textStyle, color = Color.DarkGray)
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent

        ),

        singleLine = isSingleLine,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        modifier = modifier
            .fillMaxWidth()

    )
}