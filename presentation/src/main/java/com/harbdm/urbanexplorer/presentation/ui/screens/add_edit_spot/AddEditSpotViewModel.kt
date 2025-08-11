package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harbdm.urbanexplorer.domain.model.InvalidSpotException
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.usecase.SpotUseCases
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
class AddEditSpotViewModel @Inject constructor(
    private val spotUseCases: SpotUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _spotState = MutableStateFlow(AddEditSpotState())
    val spotState: StateFlow<AddEditSpotState> = _spotState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<Long>("spotId")?.let { spotId ->
            Log.d("recievedID", spotId.toString())
            if (spotId.toInt() != -1) {
                viewModelScope.launch {
                    spotUseCases.getSpotById(spotId)?.also { spot ->
                        _spotState.update {
                            it.copy(
                                it.spotTitle.copy(
                                    text = spot.spotName,
                                    isHintVisible = false
                                ),
                                it.spotType.copy(
                                    text = spot.spotType,
                                    isHintVisible = false
                                ),
                                it.spotDescription.copy(
                                    text = spot.spotDescription ?: "",
                                    isHintVisible = false
                                ),
                                it.spotLocationHint.copy(
                                    text = spot.locationHint,
                                    isHintVisible = false
                                ),
                                spotRating = spot.spotRating,
                                spotId = spot.id,
                                spotPhotos = spot.photos
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditSpotEvent) {
        when (event) {
            is AddEditSpotEvent.OnTittleChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotTitle.copy(
                            text = event.tittle
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnTittleFocusChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotTitle.copy(
                            isHintVisible = !event.focusState.isFocused
                                    && it.spotTitle.text.isBlank()
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnTypeChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotType.copy(
                            text = event.type
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnTypeFocusChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotType.copy(
                            isHintVisible = !event.focusState.isFocused
                                    && it.spotType.text.isBlank()
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnDescriptionChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotDescription.copy(
                            text = event.description
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnDescriptionFocusChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotDescription.copy(
                            isHintVisible = !event.focusState.isFocused
                                    && it.spotDescription.text.isBlank()
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnLocationHintChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotLocationHint.copy(
                            text = event.locationHint
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnLocationHintFocusChanged -> {
                _spotState.update {
                    it.copy(
                        it.spotLocationHint.copy(
                            isHintVisible = !event.focusState.isFocused
                                    && it.spotLocationHint.text.isBlank()
                        )
                    )
                }
            }

            is AddEditSpotEvent.OnPhotoAdded -> {
                _spotState.update {
                    it.copy(
                        spotPhotos = it.spotPhotos + event.photo
                    )
                }
            }

            is AddEditSpotEvent.OnPhotoDeleted -> {
                _spotState.update {
                    it.copy(
                        spotPhotos = it.spotPhotos - event.photo
                    )
                }
            }

            is AddEditSpotEvent.OnRatingChanged -> {
                _spotState.update {
                    it.copy(
                        spotRating = event.rating
                    )
                }
            }

            AddEditSpotEvent.OnSaveSpotClicked -> {
                viewModelScope.launch {
                    try {
                        spotUseCases.addSpotWithPhotos(
                            Spot(
                                spotName = spotState.value.spotTitle.text,
                                spotType = spotState.value.spotType.text,
                                spotDescription = spotState.value.spotDescription.text,
                                locationHint = spotState.value.spotLocationHint.text,
                                spotRating = spotState.value.spotRating,
                                timeStamp = System.currentTimeMillis(),
                                photos = spotState.value.spotPhotos,
                                id = if (spotState.value.spotId.toInt() != 1) spotState.value.spotId else 0
                            )
                        )
                        _eventFlow.emit(
                            UiEvent.SaveSpotSuccess
                        )
                    } catch (e: InvalidSpotException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Can't save the spot :("
                            )
                        )
                    }
                }
            }
        }

    }


    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveSpotSuccess : UiEvent()
    }
}