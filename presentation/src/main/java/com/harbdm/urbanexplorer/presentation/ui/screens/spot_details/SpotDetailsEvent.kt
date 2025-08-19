package com.harbdm.urbanexplorer.presentation.ui.screens.spot_details

sealed interface SpotDetailsEvent {
    object OnDeleteSpot: SpotDetailsEvent
    object OnSpotRefresh: SpotDetailsEvent
}