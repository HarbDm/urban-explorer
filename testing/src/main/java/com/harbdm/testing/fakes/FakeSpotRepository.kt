package com.harbdm.testing.fakes

import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map

class FakeSpotRepository : SpotRepository {

    private val spotsFlow = MutableStateFlow<LinkedHashMap<Long, Spot>>(linkedMapOf())
    private var nextId = 1L



    override suspend fun insertSpot(spot: Spot): Long {
        val newId = nextId++
        val newSpot = spot.copy(id = newId)
        val currentSpots = spotsFlow.value
        currentSpots[newId] = newSpot
        spotsFlow.value = LinkedHashMap(currentSpots)
        return newId
    }

    override fun getAllSpotsWithPhotos(): Flow<List<Spot>> {
        return spotsFlow.asSharedFlow().map { it.values.toList().reversed() }
    }


    override suspend fun insertPhotos(photos: List<Photo>) {
        TODO("Not yet implemented")
    }

    override suspend fun getSpotWithPhotos(spotId: Long): Spot? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteSpot(spot: Spot) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePhotosForSpot(spotId: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePhotoById(photoId: Long) {
        TODO("Not yet implemented")
    }

}