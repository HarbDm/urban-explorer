package com.harbdm.urbanexplorer.domain.usecase.spot

import app.cash.turbine.test
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class GetSpotsUseCaseTest {
    private lateinit var spotRepository: SpotRepository
    private lateinit var getSpotsUseCase: GetSpotsUseCase

    @Before
    fun setUp(){
        spotRepository = mockk()
        getSpotsUseCase = GetSpotsUseCase(spotRepository)
    }

    @Test
    fun `invoke SHOULD return list with spots from repository`() = runTest {
        val inputSpotsList = listOf(
            Spot(
                spotName = "Test Park 1",
                spotType = "Park",
                spotDescription = "just a Park",
                locationHint = "around the corner",
                spotRating = 4,
                id = 0,
                timeStamp = 1234,
                photos = emptyList()
            ),
            Spot(
                spotName = "Test Park 2",
                spotType = "Park",
                spotDescription = "just a Park",
                locationHint = "around the corner",
                spotRating = 4,
                id = 1,
                timeStamp = 1235,
                photos = emptyList()
            )
        )

        coEvery { spotRepository.getAllSpotsWithPhotos() } returns flowOf(inputSpotsList)


        val result = getSpotsUseCase()

        result.test {
            val emittedList = awaitItem()

            assertEquals(2, emittedList.size)

            assertEquals("Test Park 1",emittedList[0].spotName)
            assertEquals("Test Park 2",emittedList[1].spotName)
            awaitComplete()
        }

        coVerify(exactly = 1) { spotRepository.getAllSpotsWithPhotos() }
    }
}