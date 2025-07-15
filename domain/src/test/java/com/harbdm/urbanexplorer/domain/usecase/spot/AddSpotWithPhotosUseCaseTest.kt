package com.harbdm.urbanexplorer.domain.usecase.spot

import com.harbdm.urbanexplorer.core.model.Resource
import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import com.harbdm.urbanexplorer.domain.usecase.SpotUseCases
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class AddSpotWithPhotosUseCaseTest {

    private lateinit var spotRepository: SpotRepository
    private lateinit var addSpotWithPhotosUseCase: AddSpotWithPhotosUseCase

    @Before
    fun setUp(){

        spotRepository = mockk()
        addSpotWithPhotosUseCase = AddSpotWithPhotosUseCase(spotRepository)
    }

    @Test
    fun `invoke with valid spot and photos SHOULD return correct spotOwnerId`() = runTest {
        //fake data
        val fakeSpotId = 12L
        val inputPhotos = listOf(
            Photo(
                photoId = 0,
                spotOwnerId = 0,
                uriString = "uri1",
                caption = null
            )
        )
        val inputSpot = Spot(
            spotName = "Test Park",
            spotType = "Park",
            spotDescription = "just a Park",
            locationHint = "around the corner",
            spotRating = 4,
            id = 0,
            timeStamp = 1234,
            photos = inputPhotos
        )
        val photoListSlot = slot<List<Photo>>()


        coEvery { spotRepository.insertSpot(any()) } returns fakeSpotId
        coEvery { spotRepository.insertPhotos(capture(photoListSlot)) } returns Unit

        val result = addSpotWithPhotosUseCase(inputSpot)

        coVerify {
            spotRepository.insertSpot(any())
            spotRepository.insertPhotos(any())
        }

        val capturedPhotos = photoListSlot.captured
        assertEquals(1, capturedPhotos.size)
        assertEquals(fakeSpotId, capturedPhotos[0].spotOwnerId)
        assertEquals("uri1", capturedPhotos[0].uriString)

        assertTrue(result is Resource.Success)
        assertEquals(fakeSpotId, (result as Resource.Success).data)
    }

    @Test
    fun `invoke with no title SHOULD return error and NOT call repository`() = runTest {
        val inputPhotos = emptyList<Photo>()
        val inputSpot = Spot(
            spotName = "    ",
            spotType = "Park",
            spotDescription = "just a Park",
            locationHint = "around the corner",
            spotRating = 4,
            id = 0,
            timeStamp = 1234,
            photos = inputPhotos
        )

         val result = addSpotWithPhotosUseCase(inputSpot)

        coVerify (exactly = 0) { spotRepository.insertSpot(any()) }
        coVerify (exactly = 0) { spotRepository.insertPhotos(any()) }

        assertTrue(result is Resource.Error)
    }

    @Test
    fun `invoke with no location hint SHOULD return error and NOT call repository`() = runTest {
        val inputPhotos = emptyList<Photo>()
        val inputSpot = Spot(
            spotName = "Test Park",
            spotType = "Park",
            spotDescription = "just a Park",
            locationHint = "    ",
            spotRating = 4,
            id = 0,
            timeStamp = 1234,
            photos = inputPhotos
        )

        val result = addSpotWithPhotosUseCase(inputSpot)

        coVerify (exactly = 0) { spotRepository.insertSpot(any()) }
        coVerify (exactly = 0) { spotRepository.insertPhotos(any()) }

        assertTrue(result is Resource.Error)
    }

}