package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import androidx.annotation.StringRes
import com.harbdm.urbanexplorer.presentation.R

data class AddEditSpotTextFieldState(
    val text: String = "",
    @StringRes val hint: Int = R.string.default_hint
)