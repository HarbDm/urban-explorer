package com.harbdm.urbanexplorer.presentation.ui.screens.spots

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harbdm.urbanexplorer.domain.usecase.SpotUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class SpotsViewModel @Inject constructor(
    private val spotUseCases: SpotUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SpotsState())
    val state: StateFlow<SpotsState> = _state.asStateFlow()

    private var getSpotsJob: Job? = null


    private fun getSpots() {
        getSpotsJob?.cancel()

        getSpotsJob = spotUseCases.getSpots()
            .launchIn(viewModelScope)
    }


}