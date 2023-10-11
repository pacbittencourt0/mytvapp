package com.pacbittencourt.mytv.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowImages(
    @SerialName("medium")
    val mediumUrl: String,
    @SerialName("original")
    val originalUrl: String
)
