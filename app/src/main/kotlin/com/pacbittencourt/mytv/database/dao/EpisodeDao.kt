package com.pacbittencourt.mytv.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pacbittencourt.mytv.database.model.EpisodeEntity

@Dao
interface EpisodeDao {
    @Insert
    suspend fun insertEpisodes(vararg episode: EpisodeEntity)

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun getEpisodeById(id: Int): EpisodeEntity?
}