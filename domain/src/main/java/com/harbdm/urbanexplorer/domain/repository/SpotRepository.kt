package com.harbdm.urbanexplorer.domain.repository

import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.domain.model.Spot
import kotlinx.coroutines.flow.Flow

interface SpotRepository {

    suspend fun insertSpot(spot: Spot): Long

    suspend fun insertPhotos(photos: List<Photo>)

    fun getSpotWithPhotos(spotId: Long): Flow<Spot?>

    fun getAllSpotsWithPhotos(): Flow<List<Spot>>

    suspend fun deleteSpot(spot: Spot)

    suspend fun deletePhotosForSpot(spotId: Long)

    suspend fun deletePhotoById(photoId: Long)
}