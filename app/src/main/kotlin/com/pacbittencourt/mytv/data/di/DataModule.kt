package com.pacbittencourt.mytv.data.di

import com.pacbittencourt.mytv.data.repository.EpisodeRepository
import com.pacbittencourt.mytv.data.repository.NextEpisodeRepository
import com.pacbittencourt.mytv.data.repository.SearchRepository
import com.pacbittencourt.mytv.data.repository.ShowRepository
import com.pacbittencourt.mytv.data.repository.defaultimpl.EpisodeRepositoryDefault
import com.pacbittencourt.mytv.data.repository.defaultimpl.NextEpisodeRepositoryDefault
import com.pacbittencourt.mytv.data.repository.defaultimpl.SearchRepositoryImpl
import com.pacbittencourt.mytv.data.repository.defaultimpl.ShowRepositoryDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository

    @Binds
    fun bindsShowRepository(
        repository: ShowRepositoryDefault
    ): ShowRepository

    @Binds
    fun bindsNextEpisodeRepository(
        repository: NextEpisodeRepositoryDefault
    ): NextEpisodeRepository

    @Binds
    fun bindsEpisodeRepository(
        repository: EpisodeRepositoryDefault
    ): EpisodeRepository
}