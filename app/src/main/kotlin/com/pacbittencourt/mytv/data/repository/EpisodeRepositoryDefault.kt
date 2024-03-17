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
        episodeDao.getEpisodesFromShowById(showId).collect { list ->
            if (list.isNotEmpty()) {
                val filteredList = list
                    .filter { it.id > episodeId }
                    .sortedBy { it.id }
                if (filteredList.isNotEmpty()) {
                    val nextEpisode = filteredList.first()
                    nextDao.insertNext(NextEntity(showId, nextEpisode.id))
                } else {
                    nextDao.deleteNext(showId)
                }
            }
        }
    }

    override suspend fun unMarkEpisodeWatched() {

    }
}