package com.example.shalawat.domain.usecase

import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.repository.ShalawatRepository
import javax.inject.Inject

class DeleteShalawatUseCase @Inject constructor(
    private val repository: ShalawatRepository
) {
    /**
     * Deletes a shalawat entry and its associated audio file
     * if the audio source is internal storage.
     */
    suspend operator fun invoke(shalawat: Shalawat) {
        if (shalawat.audioSource == "internal") {
            repository.deleteAudioFile(shalawat.audioFileName)
        }
        repository.delete(shalawat)
    }
}
