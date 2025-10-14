package com.harbdm.urbanexplorer.core.domain.usecase.spot

import com.harbdm.urbanexplorer.core.domain.model.Spot
import com.harbdm.urbanexplorer.core.domain.repository.SpotRepository


class DeleteSpotUseCase(
    private val spotRepository: SpotRepository
) {
    suspend operator fun invoke(spot: Spot) {
        spotRepository.deleteSpot(spot)
    }
}