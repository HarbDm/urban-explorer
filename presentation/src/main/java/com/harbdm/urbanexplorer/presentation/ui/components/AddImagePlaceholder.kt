package com.harbdm.urbanexplorer.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.harbdm.urbanexplorer.presentation.R

/**
 * Placeholder used in [PhotoCarousel] composable.
 * Have "add" function to add photo from gallery or camera provided via callbacks.
 */
@Composable
fun AddImagePlaceholder(
    onPhotoFromCamera: () -> Unit,
    onPhotoFromGallery: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxHeight()
            .width(200.dp)
    ) {
        AsyncImage(
            model = R.drawable.add_image_placeholder,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.add_image_placeholder),
            modifier = Modifier
                .clickable(
                    onClick = { isMenuExpanded = true }
                )

        )

        DropdownMenu(
            expanded = isMenuExpanded,
            onDismissRequest = { isMenuExpanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.photo_from_camera),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                onClick = {
                    isMenuExpanded = false
                    onPhotoFromCamera()
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = null
                    )
                },
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.photo_from_gallery),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                onClick = {
                    isMenuExpanded = false
                    onPhotoFromGallery()
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.PhotoLibrary,
                        contentDescription = null
                    )
                },
            )
        }
    }
}