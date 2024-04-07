package com.pacbittencourt.mytv.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("season")
    val season: Int,
    @SerialName("number")
    val number: Int,
    @SerialName("summary")
    val summary: String?,
    @SerialName("runtime")
    val runtime: Int?,
    @SerialName("image")
    val images: ShowImages?,
    @SerialName("airstamp")
    val airTimestamp: String,
)
