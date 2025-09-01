package com.harbdm.urbanexplorer.presentation.ui.screens.about

sealed interface AboutEvents {
    object OnShowsnackbar: AboutEvents

    object OnAddTestSpot: AboutEvents
}