package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.network.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchShow(query: String): Flow<List<SearchResult>>
}