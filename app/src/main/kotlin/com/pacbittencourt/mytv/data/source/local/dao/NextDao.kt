package com.pacbittencourt.mytv.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pacbittencourt.mytv.data.source.local.model.NextEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNext(next: NextEntity)

    @Query("SELECT * FROM next_episode")
    fun getAll(): Flow<List<NextEntity>>

    @Query("DELETE FROM next_episode WHERE showId = :showId")
    fun deleteNext(showId: Int)
}