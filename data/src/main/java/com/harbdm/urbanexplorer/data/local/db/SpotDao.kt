package com.harbdm.urbanexplorer.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.harbdm.urbanexplorer.data.local.db.entity.PhotoEntity
import com.harbdm.urbanexplorer.data.local.db.entity.SpotEntity
import com.harbdm.urbanexplorer.data.local.db.relation.SpotWithPhotos
import kotlinx.coroutines.flow.Flow


/*
*PhotoEntity operations are currently located here because they're coupled
*with SpotEntity and doesn't exist outside of Spot
*
*In case of additional features that require photos without spots (e.g. gallery)
*consider extracting PhotoEntity logic into PhotoDao
* */

@Dao
interface SpotDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpot(spot: SpotEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoEntity>)

    @Transaction
    @Query(
        """
        SELECT * FROM spots 
        WHERE id = :spotId
        """
    )
    fun getSpotWithPhotos(spotId: Long): Flow<SpotWithPhotos?>

    @Transaction
    @Query(
        """
        SELECT * FROM spots 
        ORDER BY time_stamp DESC
        """
    )
    fun getAllSpotsWithPhotos(): Flow<List<SpotWithPhotos>>

    @Delete
    suspend fun deleteSpot(spot: SpotEntity)

    @Query(
        """
        DELETE FROM photos 
        WHERE spot_owner_id = :spotId
        """
    )
    suspend fun deletePhotosForSpot(spotId: Long)

    @Query(
        """
        DELETE FROM photos 
        WHERE id = :photoId
        """
    )
    suspend fun deletePhotoById(photoId: Long)
}