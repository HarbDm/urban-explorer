package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.harbdm.urbanexplorer.presentation.R
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUiProvider
import com.harbdm.urbanexplorer.presentation.shell.LocalShellViewModel
import com.harbdm.urbanexplorer.presentation.shell.LocalTopAppBarController
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarAction
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarState
import com.harbdm.urbanexplorer.presentation.ui.components.PhotoCarousel
import com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components.HintTextField
import com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components.RatingSlider
import com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components.TypeDropdownMenu
import kotlinx.coroutines.flow.collectLatest
import java.io.File

@Composable
fun AddEditSpotScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddEditSpotViewModel = hiltViewModel()

) {
    Log.d("recomposition", "recomp in addeditscreen")
    val uiState by viewModel.spotState.collectAsState()

    val topAppBarController = LocalTopAppBarController.current

    val urbanExplorerShellViewModel = LocalShellViewModel.current

    Log.d("ViewModelCheck", "Screen shell ViewModel instance: $urbanExplorerShellViewModel")

    val context = LocalContext.current

    val cameraImageUri = remember {
        val file = File(context.cacheDir, "camera_photo_${System.currentTimeMillis()}.jpg")
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                viewModel.onEvent(AddEditSpotEvent.OnPhotoAdded(cameraImageUri.toString()))
            }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                viewModel.onEvent(AddEditSpotEvent.OnPhotoAdded(uri.toString()))
            }
        }
    )
    LaunchedEffect(uiState.spotLoading) {
        if (!uiState.spotLoading)
            topAppBarController.update(
                TopAppBarState(
                    title = if (viewModel.spotState.value.spotId.toInt() != -1) "Edit Spot" else "New Spot",
                    isBackButtonVisible = true,
                    actions = listOf(
                        TopAppBarAction.IconAction(
                            icon = Icons.Default.Save,
                            contentDescription = "Save",
                            onClick = {
                                viewModel.onEvent(AddEditSpotEvent.OnSaveSpotClicked)
                            }
                        )
                    )
                )
            )
    }
    LaunchedEffect(Unit) {

        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditSpotViewModel.UiEvent.ShowSnackbar -> {
                    urbanExplorerShellViewModel.showSnackbar(event.message)
                }

                is AddEditSpotViewModel.UiEvent.SaveSpotSuccess -> {
                    onNavigateBack()
                }

                is AddEditSpotViewModel.UiEvent.LaunchCamera -> {
                    cameraLauncher.launch(cameraImageUri)
                }

                is AddEditSpotViewModel.UiEvent.LaunchGallery -> {
                    galleryLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            }
        }
    }
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        HintTextField(
            text = uiState.spotTitle.text,
            hint = uiState.spotTitle.hint,
            label = R.string.tittle,
            onValueChange = { newTitle ->
                viewModel.onEvent(AddEditSpotEvent.OnTittleChanged(newTitle))
            },
            textStyle = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        TypeDropdownMenu(
            typesList = SpotTypeUiProvider.all(),
            textStyle = MaterialTheme.typography.bodyLarge,
            currentType = uiState.spotType,
            onTypePicked = { type->
                viewModel.onEvent(AddEditSpotEvent.OnTypeChanged(type))
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        HintTextField(
            text = uiState.spotDescription.text,
            hint = uiState.spotDescription.hint,
            label = R.string.description,
            onValueChange = { newDescription ->
                viewModel.onEvent(AddEditSpotEvent.OnDescriptionChanged(newDescription))
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            isSingleLine = false,
            modifier = Modifier.height(150.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        PhotoCarousel(
            onPhotoFromCamera = { viewModel.onEvent(AddEditSpotEvent.OnCameraClicked) },
            onPhotoFromGallery = { viewModel.onEvent(AddEditSpotEvent.OnGalleryClicked) },
            photos = uiState.spotPhotos,
            haveAddImagePlaceholder = true,
            modifier = Modifier.height(150.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        HintTextField(
            text = uiState.spotLocationHint.text,
            hint = uiState.spotLocationHint.hint,
            label = R.string.location_hint,
            onValueChange = { newLocationHint ->
                viewModel.onEvent(AddEditSpotEvent.OnLocationHintChanged(newLocationHint))
            },
            textStyle = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        RatingSlider(
            currentRating = uiState.spotRating,
            ratingRange = 0f..10f,
            onRatingChanged = { newRating ->
                viewModel.onEvent(AddEditSpotEvent.OnRatingChanged(newRating))
            }
        )

    }
}

