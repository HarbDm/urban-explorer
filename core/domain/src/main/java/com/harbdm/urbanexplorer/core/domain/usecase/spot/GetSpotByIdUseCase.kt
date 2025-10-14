package com.harbdm.urbanexplorer.core.domain.usecase.spot

import com.harbdm.urbanexplorer.core.domain.model.Spot
import com.harbdm.urbanexplorer.core.domain.repository.SpotRepository


class GetSpotByIdUseCase(
    private val spotRepository: SpotRepository
) {
    suspend operator fun invoke(spotId: Long): Spot?{
        return spotRepository.getSpotWithPhotos(spotId)
    }
}