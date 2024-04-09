package com.pacbittencourt.mytv.data.source.remote.api

import com.pacbittencourt.mytv.data.source.remote.model.SearchResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchApi @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun searchShow(query: String): Flow<List<SearchResult>> {
        return flow {
            emit(client.get(urlString = "https://api.tvmaze.com/search/shows/?q=$query").body())
        }
    }
}