package com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.add_edit_spot.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.harbdm.urbanexplorer.feature.spots.R

/**
 * Building around default [Slider]. Using custom slider with pointer and background, but
 * relly on [Slider] functionality.
 *
 * Notes:
 *  -If more control needed consider moving to fully custom slider via Canvas()
 *   and .detectDragGestures()
 */
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
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = currentRating.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Slider(
            value = currentRating.toFloat(),
            onValueChange = { onRatingChanged(it.toInt()) },
            steps = 0,
            valueRange = ratingRange,
            colors = SliderDefaults.colors(
                //hide all default slider UI elements to draw our own
                thumbColor = Color.Transparent,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .drawWithContent {
                    // background calculations
                    val trackHeight = 4.dp.toPx()
                    val y = size.height / 2 - trackHeight / 2
                    val activeWidth = (currentRating - ratingRange.start) /
                            (ratingRange.endInclusive - ratingRange.start) * size.width

                    // filled part of the background
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(0f, y),
                        size = Size(activeWidth, trackHeight)
                    )

                    // empty part of the background
                    drawRect(
                        color = Color(0xFFDBE0E5),
                        topLeft = Offset(activeWidth, y),
                        size = Size(size.width - activeWidth, trackHeight)
                    )

                    // pointer calculations
                    val thumbRadius = 4.dp.toPx()
                    val percent = (currentRating - ratingRange.start) / (ratingRange.endInclusive - ratingRange.start)
                    val thumbX = size.width * percent
                    val thumbY = size.height / 2

                    drawCircle(
                        color = Color.Gray,
                        radius = thumbRadius,
                        center = Offset(thumbX, thumbY)
                    )

                    drawContent()
                }
        )
    }
}
