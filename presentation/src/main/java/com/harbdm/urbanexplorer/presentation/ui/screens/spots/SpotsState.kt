package com.harbdm.urbanexplorer.presentation.ui.screens.spots

import com.harbdm.urbanexplorer.domain.model.Spot

data class SpotsState (
    val spots: List<Spot> = emptyList()
)