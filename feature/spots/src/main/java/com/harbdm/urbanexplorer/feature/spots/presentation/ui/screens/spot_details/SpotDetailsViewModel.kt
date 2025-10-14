package com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spot_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harbdm.urbanexplorer.core.domain.model.InvalidSpotException
import com.harbdm.urbanexplorer.core.domain.usecase.SpotUseCases
import com.harbdm.urbanexplorer.core.ui.model.SpotTypeUiProvider
import com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spot_details.SpotDetailsViewModel.UiEvent.*
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
class SpotDetailsViewModel @Inject constructor(
    private val spotUseCases: SpotUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _spotState = MutableStateFlow(SpotDetailsState())
    val spotState: StateFlow<SpotDetailsState> = _spotState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Long>("spotId")?.let { spotId ->
            viewModelScope.launch {
                spotUseCases.getSpotById(spotId)?.also { spot ->
                    _spotState.update {
                        it.copy(
                            spot = spot,
                            // after receiving spot Type from db adding [SpotTypeUi] to state
                            // to provide to UI type with icon instead of plane text
                            spotType = SpotTypeUiProvider.fromString(spot.spotType)
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: SpotDetailsEvent) {
        when (event){
            SpotDetailsEvent.OnDeleteSpot -> {
                viewModelScope.launch {
                    try {
                        // For now we're entering this screen only for existing spot, so
                        // spot cant be null.
                        // CHANGE IT IF ALLOWING ENTER SCREEN WITH NO SPOT!!
                        spotUseCases.deleteSpot(_spotState.value.spot!!)
                        _eventFlow.emit(
                            DeleteSpotSuccess
                        )
                    } catch (e: InvalidSpotException) {
                        _eventFlow.emit(
                            ShowSnackbar(
                                message = e.message ?: "Can't save the spot :("
                            )
                        )
                    }
                }
            }

            /**
             * Work around to refresh page when we navigating back from spot editing
             * to display actual information.
             */
            SpotDetailsEvent.OnSpotRefresh -> {
                viewModelScope.launch {
                    spotUseCases.getSpotById(_spotState.value.spot?.id?: -1)?.also { spot ->
                        _spotState.update {
                            it.copy(
                                spot = spot,
                                spotType = SpotTypeUiProvider.fromString(spot.spotType)
                            )
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String) : UiEvent()
        object DeleteSpotSuccess: UiEvent()
    }
}