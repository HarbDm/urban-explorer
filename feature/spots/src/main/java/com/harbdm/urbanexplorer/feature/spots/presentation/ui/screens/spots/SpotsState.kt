package com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spots

import com.harbdm.urbanexplorer.core.domain.model.Spot

data class SpotsState (
    val spots: List<Spot> = emptyList()
)