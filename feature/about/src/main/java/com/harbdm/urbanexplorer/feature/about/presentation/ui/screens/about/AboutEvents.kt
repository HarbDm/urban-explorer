package com.harbdm.urbanexplorer.feature.about.presentation.ui.screens.about

sealed interface AboutEvents {
    object OnShowsnackbar: AboutEvents

    object OnAddTestSpot: AboutEvents
}