package com.harbdm.urbanexplorer.app.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harbdm.urbanexplorer.core.ui.TopAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UrbanExplorerShellViewModel @Inject constructor(

) : ViewModel() {

    private val _topAppBarState = MutableStateFlow(TopAppBarState())
    val topAppBarState: StateFlow<TopAppBarState>  = _topAppBarState.asStateFlow()

    private val _snackbarMessages = MutableSharedFlow<String>()
    val snackbarMessages = _snackbarMessages.asSharedFlow()

    fun updateAppBar(newState: TopAppBarState){
        _topAppBarState.update { newState }
    }

    fun resetAppBar() {
        _topAppBarState.update { TopAppBarState() }
    }

    fun showSnackbar(message: String){
        viewModelScope.launch {
            _snackbarMessages.emit(message)
        }
    }
}

