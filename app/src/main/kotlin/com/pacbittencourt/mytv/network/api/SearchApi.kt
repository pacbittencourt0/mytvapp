package com.pacbittencourt.mytv.network.api

import com.pacbittencourt.mytv.network.model.SearchResult
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
    private val url: SearchEndpoint,
) {
    suspend fun searchShow(query: String): Flow<List<SearchResult>> {
        return flow {
            emit(client.get(urlString = url.searchShow(query)).body())
        }
    }
}