package com.pacbittencourt.mytv.network.api

import com.pacbittencourt.mytv.network.BaseEndpoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchEndpoint @Inject constructor() : BaseEndpoint() {
    fun searchShow(query: String): String {
        return "${BASE_URL}/search/shows/?q=$query"
    }
}