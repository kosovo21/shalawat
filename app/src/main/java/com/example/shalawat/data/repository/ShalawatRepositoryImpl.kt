package com.example.shalawat.data.repository

import android.net.Uri
import com.example.shalawat.data.audio.AudioFileManager
import com.example.shalawat.data.local.ShalawatDao
import com.example.shalawat.data.local.ShalawatEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository interface for Shalawat data operations.
 *
 * This interface will be moved to the domain layer in Phase 3,
 * where it will operate on domain models instead of entities.
 */
interface ShalawatRepository {
    fun getAllShalawat(): Flow<List<ShalawatEntity>>
    suspend fun getShalawatById(id: Int): ShalawatEntity?
    suspend fun insert(shalawat: ShalawatEntity)
    suspend fun update(shalawat: ShalawatEntity)
    suspend fun delete(shalawat: ShalawatEntity)
    fun copyAudioFile(uri: Uri): String
    fun deleteAudioFile(fileName: String)
}

@Singleton
class ShalawatRepositoryImpl @Inject constructor(
    private val dao: ShalawatDao,
    private val audioFileManager: AudioFileManager
) : ShalawatRepository {

    override fun getAllShalawat(): Flow<List<ShalawatEntity>> {
        return dao.getAllShalawat()
    }

    override suspend fun getShalawatById(id: Int): ShalawatEntity? {
        return dao.getShalawatById(id)
    }

    override suspend fun insert(shalawat: ShalawatEntity) {
        dao.insert(shalawat)
    }

    override suspend fun update(shalawat: ShalawatEntity) {
        dao.update(shalawat)
    }

    override suspend fun delete(shalawat: ShalawatEntity) {
        dao.delete(shalawat)
    }

    override fun copyAudioFile(uri: Uri): String {
        return audioFileManager.copyAudioFile(uri)
    }

    override fun deleteAudioFile(fileName: String) {
        audioFileManager.deleteAudioFile(fileName)
    }
}
