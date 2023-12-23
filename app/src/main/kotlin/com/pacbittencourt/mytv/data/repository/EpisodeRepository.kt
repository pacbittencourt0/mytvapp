package com.pacbittencourt.mytv.data.repository

interface EpisodeRepository {

    suspend fun markEpisodeWatched(showId: Int, episodeId: Int)

    suspend fun unMarkEpisodeWatched()
}