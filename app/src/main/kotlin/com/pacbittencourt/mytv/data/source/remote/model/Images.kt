package com.pacbittencourt.mytv.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("medium")
    val mediumUrl: String?,
    @SerialName("original")
    val originalUrl: String?
)
