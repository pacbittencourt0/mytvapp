package com.pacbittencourt.mytv.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    @SerialName("score")
    val score: Double,
    @SerialName("show")
    val show: ShowNetwork
)