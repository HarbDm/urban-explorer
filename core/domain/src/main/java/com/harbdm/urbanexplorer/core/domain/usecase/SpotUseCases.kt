package com.harbdm.urbanexplorer.core.domain.usecase

import com.harbdm.urbanexplorer.core.domain.usecase.spot.AddSpotWithPhotosUseCase
import com.harbdm.urbanexplorer.core.domain.usecase.spot.DeleteSpotUseCase
import com.harbdm.urbanexplorer.core.domain.usecase.spot.GetSpotByIdUseCase
import com.harbdm.urbanexplorer.core.domain.usecase.spot.GetSpotsUseCase

data class SpotUseCases(
    val addSpotWithPhotos: AddSpotWithPhotosUseCase,
    val deleteSpot: DeleteSpotUseCase,
    val getSpots: GetSpotsUseCase,
    val getSpotById: GetSpotByIdUseCase
)