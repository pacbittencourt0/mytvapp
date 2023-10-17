package com.pacbittencourt.mytv.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pacbittencourt.mytv.database.model.ShowEntity

@Dao
interface ShowDao {

    @Insert
    suspend fun insertShow(show: ShowEntity)

    @Query("SELECT * FROM show WHERE id = :id")
    suspend fun getShowById(id: Int): ShowEntity?
}