package com.pacbittencourt.mytv.data.model

data class NextEpisodeModel(
    val showName: String,
    val season: Int,
    val episodeInSeason: Int,
    val episodeName: String,
    val image: String,
)