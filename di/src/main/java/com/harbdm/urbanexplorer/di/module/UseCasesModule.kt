package com.harbdm.urbanexplorer.di.module

import com.harbdm.urbanexplorer.core.domain.repository.PhotoStorageRepository
import com.harbdm.urbanexplorer.core.domain.repository.SpotRepository
import com.harbdm.urbanexplorer.core.domain.usecase.FileUseCases
import com.harbdm.urbanexplorer.core.domain.usecase.spot.AddSpotWithPhotosUseCase
import com.harbdm.urbanexplorer.core.domain.usecase.spot.DeleteSpotUseCase
import com.harbdm.urbanexplorer.core.domain.usecase.spot.GetSpotsUseCase
import com.harbdm.urbanexplorer.core.domain.usecase.SpotUseCases
import com.harbdm.urbanexplorer.core.domain.usecase.files.SavePhotoUseCase
import com.harbdm.urbanexplorer.core.domain.usecase.spot.GetSpotByIdUseCase
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

    @Provides
    @Singleton
    fun provideFileUseCases(repository: PhotoStorageRepository): FileUseCases{
        return FileUseCases(
            savePhotoUseCase = SavePhotoUseCase(repository)
        )
    }
}