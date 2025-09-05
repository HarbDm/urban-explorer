package com.harbdm.urbanexplorer

import android.content.Context
import com.harbdm.testing.fakes.FakeSpotRepository
import com.harbdm.urbanexplorer.data.repository.PhotoStorageRepositoryImpl
import com.harbdm.urbanexplorer.di.module.RepositoryModule
import com.harbdm.urbanexplorer.domain.repository.PhotoStorageRepository
import com.harbdm.urbanexplorer.domain.repository.SpotRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [RepositoryModule::class])
object TestRepoModule {
    private val sharedFake by lazy { FakeSpotRepository() }

    @Provides
    @Singleton
    fun provideSpotRepository(): SpotRepository = sharedFake

    @Provides
    @Singleton
    fun provideFileRepository(
        @ApplicationContext context: Context
    ) : PhotoStorageRepository{
        return PhotoStorageRepositoryImpl(context)
    }
}
