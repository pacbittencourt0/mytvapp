package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.network.api.SearchApi
import com.pacbittencourt.mytv.network.model.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun searchShow(query: String): Flow<List<SearchResult>> {
        return flowOf(searchApi.searchShow(query))
    }
}