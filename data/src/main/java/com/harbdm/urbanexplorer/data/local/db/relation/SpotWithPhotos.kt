package com.harbdm.urbanexplorer.data.local.db.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.harbdm.urbanexplorer.data.local.db.entity.PhotoEntity
import com.harbdm.urbanexplorer.data.local.db.entity.SpotEntity

/**
 * Represents a Spot along with all photos related to it.
 *
 * Used to load and delete spot with all photos in one query.
 */
data class SpotWithPhotos(
    @Embedded
    val spot: SpotEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "spot_owner_id"
    )
    val photos: List<PhotoEntity>
)