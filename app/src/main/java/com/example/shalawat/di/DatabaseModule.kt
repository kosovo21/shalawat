package com.example.shalawat.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.shalawat.data.local.DatabaseSeeder
import com.example.shalawat.data.local.ShalawatDao
import com.example.shalawat.data.local.ShalawatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideShalawatDatabase(
        @ApplicationContext context: Context,
        daoProvider: Provider<ShalawatDao>
    ): ShalawatDatabase {
        return Room.databaseBuilder(
            context,
            ShalawatDatabase::class.java,
            "shalawat_database"
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
                        DatabaseSeeder.seed(daoProvider.get())
                    }
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideShalawatDao(database: ShalawatDatabase): ShalawatDao {
        return database.shalawatDao()
    }
}
