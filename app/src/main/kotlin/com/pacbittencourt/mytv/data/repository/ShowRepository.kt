package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.data.source.local.model.ShowEntity

interface ShowRepository {
    suspend fun insertShowToWatch(show: ShowModel)
    suspend fun removeShowFromWatch(id: Int)
    suspend fun getShowById(id: Int): ShowEntity?
}