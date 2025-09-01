package com.harbdm.urbanexplorer.presentation.ui.screens.about

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harbdm.urbanexplorer.domain.model.InvalidSpotException
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.usecase.SpotUseCases
import com.harbdm.urbanexplorer.presentation.model.SpotTypeUiProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor(
    private val spotUseCases: SpotUseCases
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFLow = _eventFlow.asSharedFlow()

    fun onEvent(event: AboutEvents){
        when(event){
            AboutEvents.OnAddTestSpot -> {
                viewModelScope.launch {
                    try {
                      val addSpotResponse = spotUseCases.addSpotWithPhotos(
                          Spot(
                              spotName = "Test Spot ${System.currentTimeMillis() % 100}",
                              spotType = SpotTypeUiProvider.randomTypeKey(),
                              spotDescription = "Really cute test spot created to test things. We created that from view btw. Time of crteation ${System.currentTimeMillis()} ",
                              locationHint = "Right there in fragment",
                              spotRating = (1..10).random(),
                              id = 0,
                              timeStamp = System.currentTimeMillis()
                          )
                      )
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = "Spot with id ${addSpotResponse.data} added!"
                            )
                        )
                    } catch (e: Exception) {
                       _eventFlow.emit(
                           UiEvent.ShowSnackbar(
                               message = e.message ?: "Can't save the spot :("
                           )
                       )
                    }
                }
            }
            AboutEvents.OnShowsnackbar -> {
                viewModelScope.launch {
                _eventFlow.emit(

                    UiEvent.ShowSnackbar(
                        message = "Hey, we're in view, but snackbar controller hosted in compose!"
                    )
                )
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()

        data class TestSpotAddedShowSnackbar(val message:String): UiEvent()
    }
}