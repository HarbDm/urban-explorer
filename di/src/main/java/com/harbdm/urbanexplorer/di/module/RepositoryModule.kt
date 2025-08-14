package com.harbdm.urbanexplorer.di.module

import android.content.Context
import com.harbdm.urbanexplorer.data.local.db.UrbanExplorerDatabase
import com.harbdm.urbanexplorer.data.repository.PhotoStorageRepositoryImpl
import com.harbdm.urbanexplorer.data.repository.SpotRepositoryImpl
import com.harbdm.urbanexplorer.domain.repository.PhotoStorageRepository
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideFileRepository(
        @ApplicationContext context: Context
    ) : PhotoStorageRepository{
        return PhotoStorageRepositoryImpl(context)
    }
}