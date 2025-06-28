package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository

class DeleteSpotUseCase(
    private val spotRepository: SpotRepository
) {
    suspend operator fun invoke(spot: Spot) {
        spotRepository.deleteSpot(spot)
    }
}