package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.NextEpisodeModel
import kotlinx.coroutines.flow.Flow

interface NextEpisodeRepository {
    fun getNextEpisodeList(): Flow<List<NextEpisodeModel>>
}