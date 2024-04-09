package com.pacbittencourt.mytv.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "episodes"
)
data class EpisodeEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val watched: Boolean = false,
    val season: Int,
    val episodeInSeason: Int,
    val summary: String?,
    val imageUrl: String?,
    val showId: Int
)
