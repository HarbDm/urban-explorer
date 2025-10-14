package com.harbdm.urbanexplorer.core.domain.usecase.spot

import com.harbdm.urbanexplorer.core.model.Resource
import com.harbdm.urbanexplorer.core.domain.model.InvalidSpotException
import com.harbdm.urbanexplorer.core.domain.model.Spot
import com.harbdm.urbanexplorer.core.domain.repository.SpotRepository
import kotlin.jvm.Throws

/**
 * Use case for adding spots with photos.
 *
 * Use case ensures that all required fields are not empty or contains
 * required text.
 *
 * @param spot The [com.harbdm.urbanexplorer.core.domain.model.Spot] to add.
 * @return [Resource.Success] with newly created spot owner ID.
 * @throws [com.harbdm.urbanexplorer.core.domain.model.InvalidSpotException] if some fields are not correct or empty.
 */
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

            // needed ownerId first from newly created spot to add photos after
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