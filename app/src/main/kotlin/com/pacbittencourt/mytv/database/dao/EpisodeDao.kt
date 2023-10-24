package com.pacbittencourt.mytv.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.pacbittencourt.mytv.database.model.EpisodeEntity

@Dao
interface EpisodeDao {
    @Insert
    suspend fun insertEpisodes(vararg episode: EpisodeEntity)
}