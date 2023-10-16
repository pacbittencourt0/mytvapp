package com.pacbittencourt.mytv.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.pacbittencourt.mytv.database.model.ShowEntity

@Dao
interface ShowDao {

    @Insert
    suspend fun insertShow(show: ShowEntity)
}