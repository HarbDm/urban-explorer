package com.harbdm.urbanexplorer.di.module

import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import com.harbdm.urbanexplorer.domain.usecase.spot.AddSpotWithPhotosUseCase
import com.harbdm.urbanexplorer.domain.usecase.spot.SpotUseCases
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
            addSpotWithPhotos = AddSpotWithPhotosUseCase(repository)
        )
    }
}