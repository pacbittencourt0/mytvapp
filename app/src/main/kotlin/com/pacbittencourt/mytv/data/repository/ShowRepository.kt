package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.ShowModel

interface ShowRepository {
    suspend fun insertShowToWatch(show: ShowModel)
}