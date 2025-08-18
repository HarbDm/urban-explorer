package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.core.model.Resource
import com.harbdm.urbanexplorer.domain.model.InvalidSpotException
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import kotlin.jvm.Throws

class AddSpotWithPhotosUseCase(
    private val spotRepository: SpotRepository
) {

    @Throws(InvalidSpotException::class)
    suspend operator fun invoke(spot: Spot): Resource<Long>{

        if(spot.spotName.isBlank()){
            throw InvalidSpotException("Tittle can't be empty!")
        }
        if(spot.locationHint.isBlank()){
            throw InvalidSpotException("Location hint can't be empty!")
        }
        if(spot.spotType == "unknown"){
            throw InvalidSpotException("Type can't be empty!")
        }

        return try{
            val ownerSpotId = spotRepository.insertSpot(spot)

            if (spot.photos.isNotEmpty()){
                spotRepository.insertPhotos(spot.photos.map { photo ->
                    photo.copy(
                        spotOwnerId = ownerSpotId
                    )
                })
            }
            Resource.Success(ownerSpotId)
        } catch (e: Exception){
            throw e
        }
    }
}