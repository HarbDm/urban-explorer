package com.harbdm.urbanexplorer.presentation.ui.screens.spots.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.presentation.R
import androidx.core.net.toUri

@Composable
fun SpotCard(
    spot: Spot,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(spot.id) },
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(spot.photos[0].uriString)
                    .transformations(
                        RoundedCornersTransformation(36f)
                    )
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(75.dp),
            )
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = spot.spotName,
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    text = spot.spotType,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpotCardPreview() {
    SpotCard(
        Spot(
            spotName = "Test Park",
            spotType = "Park",
            spotDescription = "TODO()",
            locationHint = "Park",
            spotRating = 3,
            id = 1,
            timeStamp = 1000,
            photos = List(1) {
                Photo(
                    photoId = 1,
                    spotOwnerId = 1,
                    uriString = "android.resource://${LocalContext.current.packageName}/${R.drawable.park_testing}",
                    caption = "test"
                )
            }
        ),
        onClick = { _-> }
    )
}