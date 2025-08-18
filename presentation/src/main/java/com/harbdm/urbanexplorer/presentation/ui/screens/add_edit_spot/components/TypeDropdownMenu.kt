package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.harbdm.urbanexplorer.presentation.R
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeDropdownMenu(
    currentType: SpotTypeUi,
    typesList: List<SpotTypeUi>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    onTypePicked: (SpotTypeUi) -> Unit,
) {
    var isMenuExpanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(
        expanded = isMenuExpanded,
        onExpandedChange = { isMenuExpanded = !isMenuExpanded },
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = if (currentType.key == "unknown") stringResource(R.string.type_hint)
            else stringResource(currentType.nameRes),
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMenuExpanded)
            },
            leadingIcon = if (currentType.key != "unknown") {
                {
                    Icon(
                        imageVector = currentType.icon,
                        contentDescription = null
                    )
                }
            } else null,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent

            ),
            singleLine = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false }
        ) {
            typesList.forEach { type ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = stringResource(type.nameRes),
                            style = textStyle
                        )
                    },
                    onClick = {
                        isMenuExpanded = false
                        onTypePicked(type)
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = type.icon,
                            contentDescription = null
                        )
                    }
                )
            }

        }
    }

    /*Box(
        modifier = modifier
            .clickable { isMenuExpanded = true }
            .fillMaxWidth()
    ) {
        Row {

                Text(
                    text = stringResource(currentType.nameRes),
                    style = textStyle
                )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )

        }

    }
    DropdownMenu(
        expanded = isMenuExpanded,
        onDismissRequest = { isMenuExpanded = false },
        modifier = modifier
    ) {
        typesList.forEach { type ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(type.nameRes),
                        style = textStyle
                    )
                },
                onClick = {
                    isMenuExpanded = false
                    onTypePicked(type)
                },
                leadingIcon = {
                    Icon(
                        imageVector = type.icon,
                        contentDescription = null
                    )
                }
            )
        }

    }*/

}