package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to retrieve continuously updated list of spots.
 *
 * @return A [Flow] that emits a `List<Spot>` every time the list of spots changes.
 */
class GetSpotsUseCase(
    private val spotRepository: SpotRepository
) {
    operator fun invoke(): Flow<List<Spot>>{
        return spotRepository.getAllSpotsWithPhotos()
    }
}