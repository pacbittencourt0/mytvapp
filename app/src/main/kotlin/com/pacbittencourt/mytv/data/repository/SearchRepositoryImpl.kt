package com.pacbittencourt.mytv.data.repository

import com.pacbittencourt.mytv.data.model.ShowModel
import com.pacbittencourt.mytv.network.api.SearchApi
import com.pacbittencourt.mytv.network.model.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val showRepository: ShowRepository
) : SearchRepository {
    override suspend fun searchShow(query: String): Flow<List<ShowModel>> {
        return searchApi.searchShow(query).map {
            it.map { result ->
                val showEntity = showRepository.getShowById(result.show.id)
                result.show.toModel(showEntity != null)
            }
        }
    }
}