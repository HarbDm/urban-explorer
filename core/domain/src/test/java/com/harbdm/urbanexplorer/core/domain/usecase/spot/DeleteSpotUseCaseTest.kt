package com.harbdm.urbanexplorer.core.domain.usecase.spot

import com.harbdm.urbanexplorer.core.domain.model.Spot
import com.harbdm.urbanexplorer.core.domain.repository.SpotRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DeleteSpotUseCaseTest {

    private lateinit var spotRepository: SpotRepository
    private lateinit var deleteSpotUseCase: DeleteSpotUseCase

    @Before
    fun setUp(){
        spotRepository = mockk()
        deleteSpotUseCase = DeleteSpotUseCase(spotRepository)
    }

    @Test
    fun `invoke with valid spot SHOULD call repository delete with proper spot`() = runTest{
        val fakeSpotId = 1
        val inputSpot = Spot(
            spotName = "Test Park",
            spotType = "Park",
            spotDescription = "just a Park",
            locationHint = "around the corner",
            spotRating = 4,
            id = 0,
            timeStamp = 1234,
            photos = emptyList()
        )

        coEvery { spotRepository.deleteSpot(inputSpot) } returns Unit

        val result = deleteSpotUseCase(inputSpot)

        coVerify(exactly = 1) { spotRepository.deleteSpot(inputSpot) }
    }

}