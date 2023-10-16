package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.ShowModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchShow(query: String): Flow<List<ShowModel>>
}