package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.network.api.SearchApi
import com.pacbittencourt.mytv.network.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {
    override suspend fun searchShow(query: String): Flow<List<ShowModel>> {
        return flowOf(searchApi.searchShow(query).map { result -> result.show.toModel() })
    }
}