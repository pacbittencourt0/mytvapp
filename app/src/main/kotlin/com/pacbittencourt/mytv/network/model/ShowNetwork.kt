package com.pacbittencourt.mytv.network.model

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
    val images: ShowImages?
)

fun ShowNetwork.toModel(isAdded: Boolean = false) = ShowModel(
    id = id,
    name = name,
    imageMediumUrl = images?.mediumUrl ?: "",
    isAdded = isAdded
)
