package com.harbdm.urbanexplorer.presentation.ui.screens.spot_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.harbdm.urbanexplorer.presentation.R
import com.harbdm.urbanexplorer.presentation.shell.LocalTopAppBarController
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarAction
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarState
import com.harbdm.urbanexplorer.presentation.ui.components.PhotoCarousel
import com.harbdm.urbanexplorer.presentation.ui.screens.spot_details.components.InfoBlockWithTittle

@Composable
fun SpotDetailsScreen(
    onEditSpotClicked: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SpotDetailsViewModel = hiltViewModel()
) {
    val uiState = viewModel.spotState.collectAsState()

    val topAppBarController = LocalTopAppBarController.current

    LaunchedEffect(Unit) {
        topAppBarController.update(
            TopAppBarState(
                title = "Spot Details",
                isBackButtonVisible = true
            )
        )

    }
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            PhotoCarousel(
                haveAddImagePlaceholder = false,
                onPhotoFromCamera = {},
                onPhotoFromGallery = {},
                photos = uiState.value.spot?.photos ?: emptyList(),
                modifier = Modifier.height(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            InfoBlockWithTittle(
                tittle = uiState.value.spot?.spotName ?: "",
                body = uiState.value.spot?.spotDescription ?: "",
                tittleStyle =  MaterialTheme.typography.labelLarge,
                bodyStyle = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            InfoBlockWithTittle(
                tittle = stringResource(R.string.location_hint),
                body = uiState.value.spot?.locationHint ?: "",
                tittleStyle =  MaterialTheme.typography.labelMedium,
                bodyStyle = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

        }
        Row(modifier = Modifier.padding(vertical = 36.dp)) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF2F2F5)
                ),
                modifier = Modifier.widthIn(min = 100.dp)
            ) {
                Text(
                    text = stringResource(R.string.edit),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDBE8F2)
                ),
                modifier = Modifier.widthIn(min = 100.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}
