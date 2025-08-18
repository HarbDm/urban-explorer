package com.harbdm.urbanexplorer.presentation.ui.screens.spot_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harbdm.urbanexplorer.domain.usecase.SpotUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpotDetailsViewModel @Inject constructor(
    private val spotUseCases: SpotUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _spotState = MutableStateFlow(SpotDetailsState())
    val spotState: StateFlow<SpotDetailsState> = _spotState.asStateFlow()

    init {
        savedStateHandle.get<Long>("spotId")?.let { spotId ->
            viewModelScope.launch {
                spotUseCases.getSpotById(spotId)?.also { spot ->
                    _spotState.update {
                        it.copy(
                            spot = spot
                        )
                    }
                }
            }
        }
    }



}