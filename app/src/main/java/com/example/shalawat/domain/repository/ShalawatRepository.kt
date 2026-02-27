package com.example.shalawat.domain.repository

import android.net.Uri
import com.example.shalawat.domain.model.Shalawat
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract for Shalawat data operations.
 * Operates on domain models — the data layer implements this interface.
 */
interface ShalawatRepository {
    fun getAllShalawat(): Flow<List<Shalawat>>
    fun searchShalawat(query: String): Flow<List<Shalawat>>
    suspend fun getShalawatById(id: Int): Shalawat?
    suspend fun insert(shalawat: Shalawat)
    suspend fun update(shalawat: Shalawat)
    suspend fun delete(shalawat: Shalawat)
    suspend fun toggleFavorite(id: Int, isFavorite: Boolean)
    fun copyAudioFile(uri: Uri): String
    fun deleteAudioFile(fileName: String)
}
