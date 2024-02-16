package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.database.dao.EpisodeDao
import com.pacbittencourt.mytv.database.dao.NextDao
import com.pacbittencourt.mytv.database.dao.ShowDao
import com.pacbittencourt.mytv.database.model.EpisodeEntity
import com.pacbittencourt.mytv.database.model.NextEntity
import com.pacbittencourt.mytv.database.model.ShowEntity
import com.pacbittencourt.mytv.network.Dispatcher
import com.pacbittencourt.mytv.network.MyTvDispatchers
import com.pacbittencourt.mytv.network.api.ShowApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowRepositoryDefault @Inject constructor(
    private val showDao: ShowDao,
    private val episodeDao: EpisodeDao,
    private val nextDao: NextDao,
    private val showApi: ShowApi,
    @Dispatcher(MyTvDispatchers.IO) private val dispatcher: CoroutineDispatcher
) : ShowRepository {
    override suspend fun insertShowToWatch(show: ShowModel) {
        withContext(dispatcher) {
            runCatching {
                showDao.insertShow(
                    ShowEntity(
                        id = show.id,
                        name = show.name,
                        imageMediumUrl = show.imageMediumUrl
                    )
                )
                val episodeList = showApi.getEpisodeList(showId = show.id)
                episodeList.map {
                    EpisodeEntity(
                        id = it.id,
                        name = it.name,
                        season = it.season,
                        episodeInSeason = it.number,
                        summary = it.summary,
                        imageUrl = it.images?.originalUrl ?: it.images?.mediumUrl,
                        showId = show.id
                    )
                }.forEach { episodeDao.insertEpisodes(it) }
                nextDao.insertNext(
                    NextEntity(
                        showId = show.id,
                        episodeId = episodeList[0].id
                    )
                )
            }
        }
    }

    override suspend fun getShowById(id: Int): ShowEntity? {
        return showDao.getShowById(id)
    }

    override suspend fun removeShowFromWatch(id: Int) {
        val show = showDao.getShowById(id)
        show?.let { showDao.deleteShow(show) }
    }
}