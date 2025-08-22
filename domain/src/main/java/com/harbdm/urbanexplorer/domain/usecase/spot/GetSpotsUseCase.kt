package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import kotlinx.coroutines.flow.Flow


class GetSpotsUseCase(
    private val spotRepository: SpotRepository
) {
    operator fun invoke(): Flow<List<Spot>>{
        return spotRepository.getAllSpotsWithPhotos()
    }
}