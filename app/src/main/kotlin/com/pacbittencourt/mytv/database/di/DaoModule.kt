package com.pacbittencourt.mytv.database.di

import com.pacbittencourt.mytv.database.MyTvDatabase
import com.pacbittencourt.mytv.database.dao.EpisodeDao
import com.pacbittencourt.mytv.database.dao.ShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesShowDao(
        database: MyTvDatabase
    ): ShowDao = database.showDao()

    @Provides
    fun providesEpisodeDao(
        database: MyTvDatabase
    ): EpisodeDao = database.episodeDao()
}