package com.pacbittencourt.mytv.data.source.remote.api

import com.pacbittencourt.mytv.data.source.remote.model.EpisodeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowApi @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getEpisodeList(showId: Int): List<EpisodeResponse> {
        return client.get(urlString = "https://api.tvmaze.com/shows/$showId/episodes").body()
    }
}