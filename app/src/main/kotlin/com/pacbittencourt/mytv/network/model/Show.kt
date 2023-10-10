package com.pacbittencourt.mytv.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Show(
    @SerialName("name")
    val name: String
)
