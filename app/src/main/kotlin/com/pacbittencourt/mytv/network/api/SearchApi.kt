package com.pacbittencourt.mytv.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

private const val BASE_URL = "https://api.tvmaze.com/search"

@Singleton
class SearchApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun searchShow(query: String = "girls") {
        return client.get("$BASE_URL/shows/?q=$query").body()
    }
}