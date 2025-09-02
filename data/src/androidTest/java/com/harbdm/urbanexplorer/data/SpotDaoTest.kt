package com.harbdm.urbanexplorer.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harbdm.urbanexplorer.data.local.db.SpotDao
import com.harbdm.urbanexplorer.data.local.db.UrbanExplorerDatabase
import com.harbdm.urbanexplorer.data.local.db.entity.SpotEntity
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class SpotDaoTest {
    private lateinit var db: UrbanExplorerDatabase
    private lateinit var dao: SpotDao

    @Before
    fun createDb(){
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UrbanExplorerDatabase::class.java
        ).build()
        dao = db.spotDao()
    }

    @After
    fun closeDb(){
        db.close()
    }

    @Test
    fun insert_and_query() = runTest{
        dao.insertSpot(SpotEntity(
            spotName = "Test Park",
            spotType = "Park",
            spotDescription = "just a Park",
            locationHint = "around the corner",
            spotRating = 4,
            id = 0,
            timeStamp = 1234,
        ))

        val spots = dao.getAllSpotsWithPhotos().first()

        assertThat(spots).hasSize(1)
    }
}