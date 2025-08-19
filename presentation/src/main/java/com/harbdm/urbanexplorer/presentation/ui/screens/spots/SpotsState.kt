package com.harbdm.urbanexplorer.presentation.ui.screens.spots

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUi
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUiProvider

data class SpotsState (
    val spots: List<Spot> = emptyList()
)