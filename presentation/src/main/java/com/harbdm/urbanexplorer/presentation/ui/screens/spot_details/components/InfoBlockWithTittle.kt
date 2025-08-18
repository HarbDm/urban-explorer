package com.harbdm.urbanexplorer.presentation.ui.screens.spot_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun InfoBlockWithTittle(
    tittle: String,
    body: String,
    tittleStyle: TextStyle,
    bodyStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = tittle,
            style = tittleStyle
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = body,
            style = bodyStyle
        )
    }
}