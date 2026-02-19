package com.example.shalawat.di

import android.content.Context
import androidx.room.Room
import com.example.shalawat.data.local.ShalawatDao
import com.example.shalawat.data.local.ShalawatDatabase
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
    fun provideShalawatDatabase(
        @ApplicationContext context: Context
    ): ShalawatDatabase {
        return Room.databaseBuilder(
            context,
            ShalawatDatabase::class.java,
            "shalawat_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShalawatDao(database: ShalawatDatabase): ShalawatDao {
        return database.shalawatDao()
    }
}
