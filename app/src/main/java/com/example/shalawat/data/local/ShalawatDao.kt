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

    @Query("SELECT * FROM shalawat WHERE title LIKE '%' || :query || '%' OR transliteration LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchShalawat(query: String): Flow<List<ShalawatEntity>>

    @Query("SELECT * FROM shalawat WHERE id = :id")
    suspend fun getShalawatById(id: Int): ShalawatEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shalawat: ShalawatEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg shalawat: ShalawatEntity)

    @Update
    suspend fun update(shalawat: ShalawatEntity)

    @Delete
    suspend fun delete(shalawat: ShalawatEntity)

    @Query("UPDATE shalawat SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: Int, isFavorite: Boolean)
}
