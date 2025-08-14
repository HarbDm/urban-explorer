package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.presentation.R

@Composable
fun PhotoCarousel(
    onPhotoFromCamera: () -> Unit,
    onPhotoFromGallery: () -> Unit,
    photos: List<Photo>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = photos){ photo ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo.uriString)
                    .transformations(
                        RoundedCornersTransformation(48f)
                    )
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = photo.caption,
                modifier = Modifier.fillMaxHeight()
                    .width(250.dp),
            )
        }
        item {
            AddImagePlaceholder(
                onPhotoFromCamera = onPhotoFromCamera,
                onPhotoFromGallery = onPhotoFromGallery
            )
        }
    }
}