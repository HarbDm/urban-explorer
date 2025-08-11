package com.harbdm.urbanexplorer.di.module

import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import com.harbdm.urbanexplorer.domain.usecase.spot.AddSpotWithPhotosUseCase
import com.harbdm.urbanexplorer.domain.usecase.spot.DeleteSpotUseCase
import com.harbdm.urbanexplorer.domain.usecase.spot.GetSpotsUseCase
import com.harbdm.urbanexplorer.domain.usecase.SpotUseCases
import com.harbdm.urbanexplorer.domain.usecase.spot.GetSpotByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideSpotUseCases(repository: SpotRepository): SpotUseCases {
        return SpotUseCases(
            addSpotWithPhotos = AddSpotWithPhotosUseCase(repository),
            deleteSpot = DeleteSpotUseCase(repository),
            getSpots = GetSpotsUseCase(repository),
            getSpotById = GetSpotByIdUseCase(repository),
        )
    }
}