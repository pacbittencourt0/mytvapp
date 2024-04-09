package com.pacbittencourt.mytv.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.pacbittencourt.mytv.data.source.local.model.EpisodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Insert
    suspend fun insertEpisodes(vararg episode: EpisodeEntity)

    @Update
    suspend fun update(vararg episode: EpisodeEntity)

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun getEpisodeById(id: Int): EpisodeEntity?

    @Query("SELECT * FROM episodes WHERE showId = :showId AND season = :season AND episodeInSeason = :number")
    suspend fun getEpisodeByNumberInSeason(showId: Int, season: Int, number: Int): EpisodeEntity?

    @Query("SELECT * FROM episodes WHERE showId = :showId")
    fun getEpisodesFromShowById(showId: Int): Flow<List<EpisodeEntity>>
}