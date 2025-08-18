package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.presentation.R
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUi
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUiProvider

data class AddEditSpotState(
    val spotTitle: AddEditSpotTextFieldState = AddEditSpotTextFieldState(hint = R.string.tittle),
    val spotDescription: AddEditSpotTextFieldState = AddEditSpotTextFieldState(hint = R.string.description_hint),
    val spotLocationHint: AddEditSpotTextFieldState = AddEditSpotTextFieldState(hint = R.string.location_hint_hint),
    val spotType: SpotTypeUi = SpotTypeUiProvider.default(),
    val spotRating: Int = 8,
    val spotId: Long = -1,
    val spotPhotos: List<Photo> = emptyList(),
    val spotLoading: Boolean = true,
)