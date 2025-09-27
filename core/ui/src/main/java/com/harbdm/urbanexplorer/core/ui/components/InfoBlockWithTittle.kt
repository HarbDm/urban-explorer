package com.harbdm.urbanexplorer.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
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
            style = tittleStyle,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = body,
            style = bodyStyle,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun InfoBlockWithTittlePreview() {
    InfoBlockWithTittle(
        tittle = "Welcome to Spotter",
        body = "What's up Boys ssssssssssssssssssssssssssssssssssssssssssssssss",
        tittleStyle =  MaterialTheme.typography.labelLarge,
        bodyStyle = MaterialTheme.typography.bodyLarge
    )
}

