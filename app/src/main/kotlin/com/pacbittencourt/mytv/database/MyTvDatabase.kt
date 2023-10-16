package com.pacbittencourt.mytv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pacbittencourt.mytv.database.dao.ShowDao
import com.pacbittencourt.mytv.database.model.ShowEntity

@Database(
    entities = [
        ShowEntity::class
    ],
    version = 1
)
abstract class MyTvDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao
}