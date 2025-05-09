package com.harbdm.urbanexplorer.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.harbdm.urbanexplorer.data.local.db.entity.PhotoEntity
import com.harbdm.urbanexplorer.data.local.db.entity.SpotEntity


@Database(
    entities = [SpotEntity::class, PhotoEntity::class],
    version = 1
)
abstract class UrbanExplorerDatabase: RoomDatabase(){
    abstract val spotDao: SpotDao
}