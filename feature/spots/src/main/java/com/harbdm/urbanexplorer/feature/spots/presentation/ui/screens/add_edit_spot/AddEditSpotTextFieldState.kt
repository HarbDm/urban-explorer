package com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.add_edit_spot

import androidx.annotation.StringRes
import com.harbdm.urbanexplorer.feature.spots.R

/**
 * Custom string state to use in TextFields with hints in case hint need to be changed.
 */
data class AddEditSpotTextFieldState(
    val text: String = "",
    @StringRes val hint: Int = R.string.default_hint
)