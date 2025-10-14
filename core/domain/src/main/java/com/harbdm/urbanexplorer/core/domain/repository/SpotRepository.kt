package com.harbdm.urbanexplorer.core.domain.repository

import com.harbdm.urbanexplorer.core.domain.model.Photo
import com.harbdm.urbanexplorer.core.domain.model.Spot
import kotlinx.coroutines.flow.Flow

interface SpotRepository {

    suspend fun insertSpot(spot: Spot): Long

    suspend fun insertPhotos(photos: List<Photo>)

    suspend fun getSpotWithPhotos(spotId: Long): Spot?

    fun getAllSpotsWithPhotos(): Flow<List<Spot>>

    suspend fun deleteSpot(spot: Spot)

    suspend fun deletePhotosForSpot(spotId: Long)

    suspend fun deletePhotoById(photoId: Long)
}