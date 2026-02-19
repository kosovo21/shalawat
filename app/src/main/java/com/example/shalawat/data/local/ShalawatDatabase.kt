package com.example.shalawat.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShalawatEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShalawatDatabase : RoomDatabase() {
    abstract fun shalawatDao(): ShalawatDao
}
