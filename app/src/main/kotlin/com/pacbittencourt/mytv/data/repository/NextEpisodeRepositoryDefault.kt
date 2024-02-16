package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.NextEpisodeModel
import com.pacbittencourt.mytv.database.dao.EpisodeDao
import com.pacbittencourt.mytv.database.dao.NextDao
import com.pacbittencourt.mytv.database.dao.ShowDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NextEpisodeRepositoryDefault @Inject constructor(
    private val nextDao: NextDao,
    private val episodeDao: EpisodeDao,
    private val showDao: ShowDao
) : NextEpisodeRepository {
    override fun getNextEpisodeList(): Flow<List<NextEpisodeModel>> {
        return nextDao.getAll().map { nextEpisodes ->
            val nextEpisodeList: MutableList<NextEpisodeModel> = mutableListOf()
            nextEpisodes.forEach {
                val epi = episodeDao.getEpisodeById(it.episodeId)
                val show = showDao.getShowById(it.showId)
                if (show != null && epi != null) {
                    nextEpisodeList.add(
                        NextEpisodeModel(
                            showName = show.name,
                            season = epi.season,
                            episodeInSeason = epi.episodeInSeason,
                            episodeName = epi.name,
                            image = show.imageMediumUrl,
                            showId = show.id,
                            episodeId = epi.id
                        )
                    )
                }
            }
            nextEpisodeList
        }
    }
}