package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.source.remote.model.EpisodeResponse
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    suspend fun markEpisodeWatched(showId: Int, episodeId: Int)

    suspend fun unMarkEpisodeWatched()

    fun getEpisodeById(episodeId: Int): Flow<EpisodeResponse>
}