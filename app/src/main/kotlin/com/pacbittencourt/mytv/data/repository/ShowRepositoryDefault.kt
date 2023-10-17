package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.database.dao.ShowDao
import com.pacbittencourt.mytv.database.model.ShowEntity
import com.pacbittencourt.mytv.network.Dispatcher
import com.pacbittencourt.mytv.network.MyTvDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowRepositoryDefault @Inject constructor(
    private val showDao: ShowDao,
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
            }
        }
    }

    override suspend fun getShowById(id: Int): ShowEntity? {
        return showDao.getShowById(id)
    }
}