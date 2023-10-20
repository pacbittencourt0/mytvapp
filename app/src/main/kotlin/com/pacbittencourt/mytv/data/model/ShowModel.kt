package com.pacbittencourt.mytv.data.model

data class ShowModel(
    val id: Int,
    val name: String,
    val imageMediumUrl: String,
    var isAdded: Boolean,
)
