package com.harbdm.urbanexplorer.data.repository

import com.harbdm.urbanexplorer.data.local.db.SpotDao
import com.harbdm.urbanexplorer.data.local.mapper.toDomainModel
import com.harbdm.urbanexplorer.data.local.mapper.toDomainModelList
import com.harbdm.urbanexplorer.data.local.mapper.toEntity
import com.harbdm.urbanexplorer.data.local.mapper.toPhotoEntityList
import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpotRepositoryImpl(
    private val dao: SpotDao
) : SpotRepository {
    override suspend fun insertSpot(spot: Spot): Long {
        return dao.insertSpot(spot.toEntity())
    }

    override suspend fun insertPhotos(photos: List<Photo>) {
        dao.insertPhotos(photos.toPhotoEntityList())
    }

    override fun getSpotWithPhotos(spotId: Long): Flow<Spot?> {
        return dao.getSpotWithPhotos(spotId).map { it?.toDomainModel() }
    }

    override fun getAllSpotsWithPhotos(): Flow<List<Spot>> {
        return dao.getAllSpotsWithPhotos().map { it.toDomainModelList() }
    }

    override suspend fun deleteSpot(spot: Spot) {
        dao.deleteSpot(spot.toEntity())
    }

    override suspend fun deletePhotosForSpot(spotId: Long) {
        dao.deletePhotosForSpot(spotId)
    }

    override suspend fun deletePhotoById(photoId: Long) {
        dao.deletePhotoById(photoId)
    }

}