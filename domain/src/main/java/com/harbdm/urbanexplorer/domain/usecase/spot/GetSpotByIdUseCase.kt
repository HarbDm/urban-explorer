package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository

/**
 * Use case to get spot with provided spot ID
 *
 * @param spotId Id of the spot to receive.
 * @return [Spot] from the source with corresponding id.
 */
class GetSpotByIdUseCase(
    private val spotRepository: SpotRepository
) {
    suspend operator fun invoke(spotId: Long): Spot?{
        return spotRepository.getSpotWithPhotos(spotId)
    }
}