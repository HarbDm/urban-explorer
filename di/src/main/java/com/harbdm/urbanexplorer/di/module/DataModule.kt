package com.harbdm.urbanexplorer.di.module

import android.app.Application
import androidx.room.Room
import com.harbdm.urbanexplorer.core.constants.AppConstants
import com.harbdm.urbanexplorer.data.local.db.UrbanExplorerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUrbanExplorerDatabase(app: Application): UrbanExplorerDatabase {
        return Room.databaseBuilder(
            app,
            UrbanExplorerDatabase::class.java,
            AppConstants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}