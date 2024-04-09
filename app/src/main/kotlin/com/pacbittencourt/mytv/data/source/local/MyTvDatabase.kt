package com.pacbittencourt.mytv.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pacbittencourt.mytv.data.source.local.dao.EpisodeDao
import com.pacbittencourt.mytv.data.source.local.dao.NextDao
import com.pacbittencourt.mytv.data.source.local.dao.ShowDao
import com.pacbittencourt.mytv.data.source.local.model.EpisodeEntity
import com.pacbittencourt.mytv.data.source.local.model.NextEntity
import com.pacbittencourt.mytv.data.source.local.model.ShowEntity

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