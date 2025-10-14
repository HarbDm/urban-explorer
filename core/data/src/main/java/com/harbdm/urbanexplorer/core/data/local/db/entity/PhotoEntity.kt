package com.harbdm.urbanexplorer.core.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
tableName = "photos",
    foreignKeys = [ForeignKey(
        entity = SpotEntity::class,
        parentColumns = ["id"],
        childColumns = ["spot_owner_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["spot_owner_id"])]
)
data class PhotoEntity(
    @ColumnInfo(name = "spot_owner_id")
    val spotOwnerId: Long,
    @ColumnInfo(name = "uri_string")
    val uriString: String,
    val caption: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)