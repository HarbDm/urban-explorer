package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import androidx.compose.ui.focus.FocusState
import com.harbdm.urbanexplorer.domain.model.Photo

sealed interface AddEditSpotEvent{
    data class OnTittleChanged(val tittle: String): AddEditSpotEvent
    data class OnTittleFocusChanged(val focusState: FocusState): AddEditSpotEvent
    data class OnTypeChanged(val type: String): AddEditSpotEvent
    data class OnTypeFocusChanged(val focusState: FocusState): AddEditSpotEvent
    data class OnDescriptionChanged(val description: String): AddEditSpotEvent
    data class OnDescriptionFocusChanged(val focusState: FocusState): AddEditSpotEvent
    data class OnLocationHintChanged(val locationHint: String): AddEditSpotEvent
    data class OnLocationHintFocusChanged(val focusState: FocusState): AddEditSpotEvent
    data class OnRatingChanged(val rating: Int): AddEditSpotEvent
    data class OnPhotoAdded(val photo: Photo): AddEditSpotEvent
    data class OnPhotoDeleted(val photo: Photo): AddEditSpotEvent
    object OnSaveSpotClicked: AddEditSpotEvent
}