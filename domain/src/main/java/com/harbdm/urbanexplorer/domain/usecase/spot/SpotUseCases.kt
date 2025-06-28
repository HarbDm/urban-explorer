package com.harbdm.urbanexplorer.domain.usecase.spot

data class SpotUseCases(
    val addSpotWithPhotos: AddSpotWithPhotosUseCase,
    val deleteSpot: DeleteSpotUseCase,
    val getSpots: GetSpotsUseCase
)
