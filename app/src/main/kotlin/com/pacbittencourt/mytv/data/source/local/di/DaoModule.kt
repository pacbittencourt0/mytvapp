package com.pacbittencourt.mytv.data.source.local.di

import com.pacbittencourt.mytv.data.source.local.MyTvDatabase
import com.pacbittencourt.mytv.data.source.local.dao.EpisodeDao
import com.pacbittencourt.mytv.data.source.local.dao.NextDao
import com.pacbittencourt.mytv.data.source.local.dao.ShowDao
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

    @Provides
    fun providesNextDao(
        database: MyTvDatabase
    ): NextDao = database.nextDao()
}