package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.domain.model.InvalidSpotException
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import kotlin.jvm.Throws

class AddSpotUseCase (
    private val spotRepository: SpotRepository
) {

    @Throws(InvalidSpotException::class)
    suspend operator fun invoke(spot: Spot): Long{
        if(spot.spotName.isBlank()){
            throw InvalidSpotException("The tittle can't be empty!")
        }
        if(spot.locationHint.isBlank()){
            throw InvalidSpotException("The location hint can't be empty!")
        }
        return spotRepository.insertSpot(spot)
    }
}