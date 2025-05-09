package com.harbdm.urbanexplorer.data.local.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.harbdm.urbanexplorer.data.local.db.entity.PhotoEntity
import com.harbdm.urbanexplorer.data.local.db.entity.SpotEntity


data class SpotWithPhotos(
    @Embedded
    val spot: SpotEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "spotOwnerId"
    )
    val photos: List<PhotoEntity>
)