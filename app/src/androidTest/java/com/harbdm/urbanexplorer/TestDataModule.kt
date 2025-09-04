package com.harbdm.urbanexplorer

import android.content.Context
import androidx.room.Room
import com.harbdm.urbanexplorer.data.local.db.UrbanExplorerDatabase
import com.harbdm.urbanexplorer.di.module.DataModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [DataModule::class])
object TestDataModule {
    @Provides @Singleton
    fun provideUrbanExplorerDatabase(@ApplicationContext ctx: Context): UrbanExplorerDatabase =
        Room.inMemoryDatabaseBuilder(ctx, UrbanExplorerDatabase::class.java).build()
}