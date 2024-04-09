package com.pacbittencourt.mytv.data.repository.defaultimpl

import com.pacbittencourt.mytv.data.repository.EpisodeRepository
import com.pacbittencourt.mytv.data.source.local.dao.EpisodeDao
import com.pacbittencourt.mytv.data.source.local.dao.NextDao
import com.pacbittencourt.mytv.data.source.local.model.NextEntity
import com.pacbittencourt.mytv.data.source.remote.api.EpisodeApi
import com.pacbittencourt.mytv.data.source.remote.model.EpisodeResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepositoryDefault @Inject constructor(
    private val episodeDao: EpisodeDao,
    private val nextDao: NextDao,
    private val episodeApi: EpisodeApi
) : EpisodeRepository {
    override suspend fun markEpisodeWatched(showId: Int, episodeId: Int) {
        val episode = episodeDao.getEpisodeById(episodeId)
        episode?.copy(watched = true)?.let {
            episodeDao.update(it)
            var season = it.season
            var epiNumber = it.episodeInSeason.plus(1)
            var nextEpisode = episodeDao.getEpisodeByNumberInSeason(it.showId, season, epiNumber)
            if (nextEpisode == null) {
                season++
                epiNumber = 1
                nextEpisode = episodeDao.getEpisodeByNumberInSeason(it.showId, season, epiNumber)
                if (nextEpisode != null) {
                    nextDao.insertNext(NextEntity(showId, nextEpisode.id))
                } else {
                    nextDao.deleteNext(showId)
                }
            } else {
                nextDao.insertNext(NextEntity(showId, nextEpisode.id))
            }
        }
    }

    override suspend fun unMarkEpisodeWatched() {

    }

    override fun getEpisodeById(episodeId: Int): Flow<EpisodeResponse> {
        return episodeApi.getEpisodeInformation(episodeId)
    }
}