package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harbdm.urbanexplorer.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingSlider(
    currentRating: Int,
    ratingRange: ClosedFloatingPointRange<Float>,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Row (modifier = Modifier.fillMaxWidth()){

            Text(
                text = stringResource(R.string.rating),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = currentRating.toString(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 16.sp
                )
            )
        }
        Slider(
            value = currentRating.toFloat(),
            onValueChange = { onRatingChanged(it.toInt()) },
            steps = 0,
            valueRange = ratingRange,
            colors = SliderDefaults.colors(
                thumbColor = Color.Transparent,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .drawWithContent {
                    val trackHeight = 4.dp.toPx()
                    val y = size.height / 2 - trackHeight / 2
                    val activeWidth = (currentRating - ratingRange.start) /
                            (ratingRange.endInclusive - ratingRange.start) * size.width

                    drawRect(
                        color = Color.Black,
                        topLeft = androidx.compose.ui.geometry.Offset(0f, y),
                        size = androidx.compose.ui.geometry.Size(activeWidth, trackHeight)
                    )

                    drawRect(
                        color = Color(0xFFDBE0E5),
                        topLeft = androidx.compose.ui.geometry.Offset(activeWidth, y),
                        size = androidx.compose.ui.geometry.Size(size.width - activeWidth, trackHeight)
                    )

                    val thumbRadius = 4.dp.toPx()
                    val percent = (currentRating - ratingRange.start) / (ratingRange.endInclusive - ratingRange.start)
                    val thumbX = size.width * percent
                    val thumbY = size.height / 2

                    drawCircle(
                        color = Color.Gray,
                        radius = thumbRadius,
                        center = androidx.compose.ui.geometry.Offset(thumbX, thumbY)
                    )

                    drawContent()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ratingpewview() {
    RatingSlider(
        currentRating = 5,
        ratingRange = 0f..10f,
        onRatingChanged = {}
    )
}