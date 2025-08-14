package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.presentation.R
import com.harbdm.urbanexplorer.presentation.shell.LocalShellViewModel
import com.harbdm.urbanexplorer.presentation.shell.LocalTopAppBarController
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarAction
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarState
import com.harbdm.urbanexplorer.presentation.shell.UrbanExplorerShellViewModel
import com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components.HintTextField
import com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.components.PhotoCarousel
import kotlinx.coroutines.flow.collectLatest
import java.io.File
import kotlin.contracts.contract

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
            file)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if(success){
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

    LaunchedEffect(Unit) {
        topAppBarController.update(
            TopAppBarState(
                title = if (viewModel.spotState.value.spotId.toInt() != -1) "Edit Spot" else "New Spot",
                isBackButtonVisible = true
            )
        )
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

        HintTextField(
            text = uiState.spotType.text,
            hint = uiState.spotType.hint,
            label = R.string.type,
            onValueChange = { newType ->
                viewModel.onEvent(AddEditSpotEvent.OnTypeChanged(newType))
            },
            textStyle = MaterialTheme.typography.bodyLarge
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


    }
}
