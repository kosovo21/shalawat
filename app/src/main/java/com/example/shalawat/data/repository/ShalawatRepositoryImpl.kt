package com.example.shalawat.data.repository

import android.net.Uri
import com.example.shalawat.data.audio.AudioFileManager
import com.example.shalawat.data.local.ShalawatDao
import com.example.shalawat.data.local.toDomain
import com.example.shalawat.data.local.toEntity
import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.repository.ShalawatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShalawatRepositoryImpl @Inject constructor(
    private val dao: ShalawatDao,
    private val audioFileManager: AudioFileManager
) : ShalawatRepository {

    override fun getAllShalawat(): Flow<List<Shalawat>> {
        return dao.getAllShalawat().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun searchShalawat(query: String): Flow<List<Shalawat>> {
        return dao.searchShalawat(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getShalawatById(id: Int): Shalawat? {
        return dao.getShalawatById(id)?.toDomain()
    }

    override suspend fun insert(shalawat: Shalawat) {
        dao.insert(shalawat.toEntity())
    }

    override suspend fun update(shalawat: Shalawat) {
        dao.update(shalawat.toEntity())
    }

    override suspend fun delete(shalawat: Shalawat) {
        dao.delete(shalawat.toEntity())
    }

    override suspend fun toggleFavorite(id: Int, isFavorite: Boolean) {
        dao.updateFavorite(id, isFavorite)
    }

    override fun copyAudioFile(uri: Uri): String {
        return audioFileManager.copyAudioFile(uri)
    }

    override fun deleteAudioFile(fileName: String) {
        audioFileManager.deleteAudioFile(fileName)
    }
}
