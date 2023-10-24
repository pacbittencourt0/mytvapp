package com.pacbittencourt.mytv.network.api

import com.pacbittencourt.mytv.network.BaseEndpoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShowEndpoint @Inject constructor() : BaseEndpoint() {
    fun episodeList(showId: Int): String {
        return "$BASE_URL/shows/$showId/episodes"
    }
}