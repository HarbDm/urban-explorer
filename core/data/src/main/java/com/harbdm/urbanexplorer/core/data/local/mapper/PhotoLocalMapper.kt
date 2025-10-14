package com.harbdm.urbanexplorer.core.data.local.mapper

import com.harbdm.urbanexplorer.core.data.local.db.entity.PhotoEntity
import com.harbdm.urbanexplorer.core.domain.model.Photo

fun PhotoEntity.toDomainModel(): Photo {
    return Photo(
        photoId = this.id,
        spotOwnerId = this.spotOwnerId,
        uriString = this.uriString,
        caption = this.caption
    )
}

fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        spotOwnerId = this.spotOwnerId,
        uriString = this.uriString,
        caption = this.caption,
        id = this.photoId
    )
}

fun List<PhotoEntity>.toDomainModelList(): List<Photo> {
    return this.map { it.toDomainModel() }
}

fun List<Photo>.toPhotoEntityList(): List<PhotoEntity> {
    return this.map { it.toEntity() }
}