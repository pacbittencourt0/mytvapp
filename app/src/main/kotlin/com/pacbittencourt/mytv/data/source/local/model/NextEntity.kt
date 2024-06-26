package com.pacbittencourt.mytv.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "next_episode"
)
data class NextEntity(
    @PrimaryKey
    val showId: Int,
    val episodeId: Int
)