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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SpotsViewModel @Inject constructor(
    private val spotUseCases: SpotUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(SpotsState())
    val state: StateFlow<SpotsState> = _state.asStateFlow()

    private var getSpotsJob: Job? = null

    init {
        getSpots()
    }

    /**
     * Pretty selfexplanatory function to get all spots, but we considering using a [Job]
     * to have only one concurrent "downloading" to keep things updated and actual.
     */
    private fun getSpots() {
        getSpotsJob?.cancel()

        getSpotsJob = spotUseCases.getSpots()
            .onEach { spots ->
                _state.update {
                    it.copy(
                        spots = spots
                    )
                }
            }
            .launchIn(viewModelScope)

    }
}