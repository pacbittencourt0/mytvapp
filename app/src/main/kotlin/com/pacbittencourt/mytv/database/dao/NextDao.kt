package com.pacbittencourt.mytv.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pacbittencourt.mytv.database.model.NextEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNext(next: NextEntity)

    @Query("SELECT * FROM next_episode")
    fun getAll(): Flow<List<NextEntity>>
}