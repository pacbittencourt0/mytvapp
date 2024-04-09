package com.pacbittencourt.mytv.data.source.remote.api

import com.pacbittencourt.mytv.data.source.remote.model.EpisodeResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeApi @Inject constructor(
    private val client: HttpClient,
) {
    fun getEpisodeInformation(episodeId: Int): Flow<EpisodeResponse> {
        return flow {
            emit(client.get(urlString = "https://api.tvmaze.com/episodes/$episodeId").body())
        }
    }
}
