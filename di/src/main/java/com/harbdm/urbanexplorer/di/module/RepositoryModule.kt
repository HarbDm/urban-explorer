package com.harbdm.urbanexplorer.di.module

import com.harbdm.urbanexplorer.data.local.db.UrbanExplorerDatabase
import com.harbdm.urbanexplorer.data.repository.SpotRepositoryImpl
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSpotRepository(db: UrbanExplorerDatabase) : SpotRepository{
        return SpotRepositoryImpl(db.spotDao)
    }

}