package com.harbdm.urbanexplorer.presentation.shell

import androidx.compose.ui.graphics.vector.ImageVector

sealed class TopAppBarAction {
    data class IconAction(
        val icon: ImageVector,
        val contentDescription: String,
        val onClick: () -> Unit
    ) : TopAppBarAction()

    data class ButtonAction(val text: String, val onClick: () -> Unit) : TopAppBarAction()
}


data class TopAppBarState(
    val title: String = "",
    val actions: List<TopAppBarAction> = emptyList(),
    val isBackButtonVisible: Boolean = false
)