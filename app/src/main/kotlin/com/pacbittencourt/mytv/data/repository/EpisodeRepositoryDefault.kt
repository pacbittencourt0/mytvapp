package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.database.dao.EpisodeDao
import com.pacbittencourt.mytv.database.dao.NextDao
import com.pacbittencourt.mytv.database.model.NextEntity
import javax.inject.Inject

class EpisodeRepositoryDefault @Inject constructor(
    private val episodeDao: EpisodeDao,
    private val nextDao: NextDao,
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
}