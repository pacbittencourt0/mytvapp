package com.pacbittencourt.mytv.data.source.remote.model

import com.pacbittencourt.mytv.data.model.ShowModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val images: Images?
)

fun ShowNetwork.toModel(isAdded: Boolean = false) = ShowModel(
    id = id,
    name = name,
    imageMediumUrl = images?.mediumUrl ?: "",
    isAdded = isAdded
)
