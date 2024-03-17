package com.pacbittencourt.mytv.data.model

data class NextEpisodeModel(
    val showName: String,
    val season: String,
    val episodeInSeason: String,
    val episodeName: String,
    val image: String,
    val showId: Int,
    val episodeId: Int,
)