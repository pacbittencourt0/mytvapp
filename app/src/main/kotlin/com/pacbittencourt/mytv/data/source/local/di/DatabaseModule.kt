package com.pacbittencourt.mytv.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.pacbittencourt.mytv.data.source.local.MyTvDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MyTvDatabase = Room.databaseBuilder(
        context,
        MyTvDatabase::class.java,
        "mytvdatabse"
    ).build()
}