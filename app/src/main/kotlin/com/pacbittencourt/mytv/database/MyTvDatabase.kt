package com.pacbittencourt.mytv.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pacbittencourt.mytv.database.dao.EpisodeDao
import com.pacbittencourt.mytv.database.dao.NextDao
import com.pacbittencourt.mytv.database.dao.ShowDao
import com.pacbittencourt.mytv.database.model.EpisodeEntity
import com.pacbittencourt.mytv.database.model.NextEntity
import com.pacbittencourt.mytv.database.model.ShowEntity

@Database(
    entities = [
        ShowEntity::class,
        EpisodeEntity::class,
        NextEntity::class
    ],
    version = 1
)
abstract class MyTvDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun nextDao(): NextDao
}