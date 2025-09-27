package com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spot_details

sealed interface SpotDetailsEvent {
    object OnDeleteSpot: SpotDetailsEvent
    object OnSpotRefresh: SpotDetailsEvent
}