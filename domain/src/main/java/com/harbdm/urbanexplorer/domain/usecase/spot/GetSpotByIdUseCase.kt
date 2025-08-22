package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository


class GetSpotByIdUseCase(
    private val spotRepository: SpotRepository
) {
    suspend operator fun invoke(spotId: Long): Spot?{
        return spotRepository.getSpotWithPhotos(spotId)
    }
}