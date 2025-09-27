package com.harbdm.urbanexplorer.feature.spots.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.harbdm.urbanexplorer.domain.model.Photo


/**
 * Reusable photo Carousel that allow you to just view photo and also to add
 * placeholder with "add" functionality.
 *
 * @param [haveAddImagePlaceholder] decides is it strictly visual or have adding functionality.
 */
@Composable
fun PhotoCarousel(
    haveAddImagePlaceholder: Boolean,
    onPhotoFromCamera: () -> Unit,
    onPhotoFromGallery: () -> Unit,
    photos: List<Photo>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = photos) { photo ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photo.uriString)
                    .transformations(
                        RoundedCornersTransformation(48f)
                    )
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = photo.caption,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp),
            )
        }
        if (haveAddImagePlaceholder) {
            item {
                AddImagePlaceholder(
                    onPhotoFromCamera = onPhotoFromCamera,
                    onPhotoFromGallery = onPhotoFromGallery
                )
            }
        }
    }
}