package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import android.net.Uri
import androidx.compose.ui.focus.FocusState
import com.harbdm.urbanexplorer.domain.model.Photo

sealed interface AddEditSpotEvent {
    data class OnTittleChanged(val tittle: String) : AddEditSpotEvent
    data class OnTypeChanged(val type: String) : AddEditSpotEvent
    data class OnDescriptionChanged(val description: String) : AddEditSpotEvent
    data class OnLocationHintChanged(val locationHint: String) : AddEditSpotEvent
    data class OnRatingChanged(val rating: Int) : AddEditSpotEvent
    data class OnPhotoAdded(val photoUri: String) : AddEditSpotEvent
    data class OnPhotoAddedFromCamera(val photoUri: String) : AddEditSpotEvent
    data class OnPhotoDeleted(val photo: Photo) : AddEditSpotEvent
    object OnGalleryClicked : AddEditSpotEvent
    object OnCameraClicked : AddEditSpotEvent
    object OnSaveSpotClicked : AddEditSpotEvent
}