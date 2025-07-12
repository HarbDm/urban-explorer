package com.harbdm.urbanexplorer.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "spots")
data class SpotEntity(
    @ColumnInfo(name = "spot_name")
    val spotName: String,
    @ColumnInfo(name = "spot_type")
    val spotType: String,
    @ColumnInfo(name = "spot_description")
    val spotDescription: String? = null,
    @ColumnInfo(name = "location_hint")
    val locationHint: String,
    @ColumnInfo(name = "spot_rating")
    val spotRating: Int,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)