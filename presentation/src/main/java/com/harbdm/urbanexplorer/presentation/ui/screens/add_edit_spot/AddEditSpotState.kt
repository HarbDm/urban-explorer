package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import com.harbdm.urbanexplorer.domain.model.Photo

data class AddEditSpotState(
    val spotTitle: AddEditSpotTextFieldState = AddEditSpotTextFieldState(hint = "Enter spot title..."),
    val spotType: AddEditSpotTextFieldState = AddEditSpotTextFieldState(hint = "Enter type..."),
    val spotDescription: AddEditSpotTextFieldState = AddEditSpotTextFieldState(hint = "Enter spot description..."),
    val spotLocationHint: AddEditSpotTextFieldState = AddEditSpotTextFieldState(hint = "Enter location hint..."),
    val spotRating: Int = 8,
    val spotId: Long = -1,
    val spotPhotos: List<Photo> = emptyList(),
    val spotLoading: Boolean = true,
)