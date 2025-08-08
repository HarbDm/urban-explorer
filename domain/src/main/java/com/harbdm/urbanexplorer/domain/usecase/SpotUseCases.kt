package com.harbdm.urbanexplorer.domain.usecase

import com.harbdm.urbanexplorer.domain.usecase.spot.AddSpotWithPhotosUseCase
import com.harbdm.urbanexplorer.domain.usecase.spot.DeleteSpotUseCase
import com.harbdm.urbanexplorer.domain.usecase.spot.GetSpotByIdUseCase
import com.harbdm.urbanexplorer.domain.usecase.spot.GetSpotsUseCase

data class SpotUseCases(
    val addSpotWithPhotos: AddSpotWithPhotosUseCase,
    val deleteSpot: DeleteSpotUseCase,
    val getSpots: GetSpotsUseCase,
    val getSpotById: GetSpotByIdUseCase
)