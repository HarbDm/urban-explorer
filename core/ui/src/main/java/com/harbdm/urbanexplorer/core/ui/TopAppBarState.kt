package com.harbdm.urbanexplorer.core.ui

import androidx.annotation.StringRes
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


sealed class TopAppBarAction {
    data class IconAction(
        val icon: ImageVector,
        val contentDescription: String,
        val onClick: () -> Unit,
        val modifier: Modifier = Modifier
    ) : TopAppBarAction()

    data class ButtonAction(
        @StringRes val text: Int,
        val onClick: () -> Unit
    ) : TopAppBarAction()
}


// Keep it as default state of app bar, should be reset to it on .reset()
data class TopAppBarState(
    val title: String = "Urban Explorer",
    val actions: List<TopAppBarAction> = emptyList(),
    val isBackButtonVisible: Boolean = false
)