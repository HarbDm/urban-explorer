package com.harbdm.urbanexplorer.presentation.shell

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UrbanExplorerShellViewModel @Inject constructor(

) : ViewModel() {


    private val _snackbarMessages = MutableSharedFlow<String>()
    val snackbarMessages = _snackbarMessages.asSharedFlow()

    fun showSnackbar(message: String){
        viewModelScope.launch {
            _snackbarMessages.emit(message)
        }
    }
}

val LocalShellViewModel = staticCompositionLocalOf<UrbanExplorerShellViewModel> {
    error("No UrbanExplorerShellViewModel provided")
}