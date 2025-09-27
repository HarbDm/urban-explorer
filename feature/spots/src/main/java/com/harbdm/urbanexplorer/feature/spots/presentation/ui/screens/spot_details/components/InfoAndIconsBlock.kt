package com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spot_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

/**
 * Block of info designed to receive Spot Type info initially,
 * but refactored to accept any map [itemMap] with text and icon to display.
 *
 * Note:
 * -If no need to use for displaying rating anymore, consider changing to accept SpotTypeId
 *  instead of Map.
 */
@Composable
fun InfoAndIconsBlock(
    tittle: String,
    itemMap: Map<String, ImageVector>,
    tittleStyle: TextStyle,
    bodyStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = tittle,
            style = tittleStyle
        )

        itemMap.forEach{ item->
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.value,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 16.dp)
                )

                Text(
                    text = item.key,
                    style = bodyStyle
                )
            }
        }
    }
}