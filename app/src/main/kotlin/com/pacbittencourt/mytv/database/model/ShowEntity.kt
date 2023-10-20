package com.pacbittencourt.mytv.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "show"
)
data class ShowEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageMediumUrl: String,
)