package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository

/**
 * Use case to delete spot from db.
 *
 * @param spot [Spot] Spot to delete.
 */
class DeleteSpotUseCase(
    private val spotRepository: SpotRepository
) {
    suspend operator fun invoke(spot: Spot) {
        spotRepository.deleteSpot(spot)
    }
}