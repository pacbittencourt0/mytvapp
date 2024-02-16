package com.pacbittencourt.mytv.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pacbittencourt.mytv.database.model.EpisodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Insert
    suspend fun insertEpisodes(vararg episode: EpisodeEntity)

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun getEpisodeById(id: Int): EpisodeEntity?

    @Query("SELECT * FROM episodes WHERE showId = :showId")
    fun getEpisodesFromShowById(showId: Int): Flow<List<EpisodeEntity>>
}