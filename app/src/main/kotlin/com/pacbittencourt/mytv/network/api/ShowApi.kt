package com.pacbittencourt.mytv.network.api

import com.pacbittencourt.mytv.network.model.EpisodeNetwork
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowApi @Inject constructor(
    private val client: HttpClient,
    private val url: ShowEndpoint
) {
    suspend fun getEpisodeList(showId: Int): List<EpisodeNetwork> {
        return client.get(urlString = url.episodeList(showId)).body()
    }
}