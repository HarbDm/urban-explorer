package com.harbdm.urbanexplorer.data.local.mapper

import com.harbdm.urbanexplorer.data.local.db.entity.SpotEntity
import com.harbdm.urbanexplorer.data.local.db.relation.SpotWithPhotos
import com.harbdm.urbanexplorer.domain.model.Spot


fun SpotEntity.toDomainModel(): Spot {
    return Spot(
        spotName = this.spotName,
        spotType = this.spotType,
        spotDescription = this.spotDescription,
        locationHint = this.locationHint,
        spotRating = this.spotRating,
        id = this.id,
        timeStamp = this.timeStamp,
        photos = emptyList()
    )
}

fun SpotWithPhotos.toDomainModel(): Spot {
    return Spot(
        spotName = this.spot.spotName,
        spotType = this.spot.spotType,
        spotDescription = this.spot.spotDescription,
        locationHint = this.spot.locationHint,
        spotRating = this.spot.spotRating,
        id = this.spot.id,
        timeStamp = this.spot.timeStamp,
        photos = this.photos.toDomainModelList()
    )
}

fun List<SpotWithPhotos>.toDomainModelList(): List<Spot> {
    return this.map { it.toDomainModel() }
}

fun Spot.toEntity(): SpotEntity {
    return SpotEntity(
        spotName = this.spotName,
        spotType = this.spotType,
        spotDescription = this.spotDescription,
        locationHint = this.locationHint,
        spotRating = this.spotRating,
        timeStamp = this.timeStamp,
        id = this.id
    )
}
