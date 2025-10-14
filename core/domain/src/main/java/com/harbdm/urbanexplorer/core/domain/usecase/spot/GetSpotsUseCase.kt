package com.harbdm.urbanexplorer.core.domain.usecase.spot

import com.harbdm.urbanexplorer.core.domain.model.Spot
import com.harbdm.urbanexplorer.core.domain.repository.SpotRepository
import kotlinx.coroutines.flow.Flow


class GetSpotsUseCase(
    private val spotRepository: SpotRepository
) {
    operator fun invoke(): Flow<List<Spot>>{
        return spotRepository.getAllSpotsWithPhotos()
    }
}