package com.example.shalawat.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ShalawatDao {

    @Query("SELECT * FROM shalawat ORDER BY createdAt DESC")
    fun getAllShalawat(): Flow<List<ShalawatEntity>>

    @Query("SELECT * FROM shalawat WHERE id = :id")
    suspend fun getShalawatById(id: Int): ShalawatEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shalawat: ShalawatEntity)

    @Update
    suspend fun update(shalawat: ShalawatEntity)

    @Delete
    suspend fun delete(shalawat: ShalawatEntity)
}
