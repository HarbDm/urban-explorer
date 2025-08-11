package com.harbdm.urbanexplorer.presentation.shell

import androidx.compose.runtime.compositionLocalOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TopAppBarController {
    private val _topAppBarState = MutableStateFlow(TopAppBarState())
    val topAppBarState: StateFlow<TopAppBarState> = _topAppBarState.asStateFlow()

    fun update(newState: TopAppBarState) {
        _topAppBarState.update { newState }
    }

    fun reset() {
        _topAppBarState.update { TopAppBarState() }
    }
}

val LocalTopAppBarController = compositionLocalOf <TopAppBarController> {
    error("No TopAppBarController provided")
}