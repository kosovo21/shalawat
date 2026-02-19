package com.example.shalawat.domain.usecase

import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.repository.ShalawatRepository
import javax.inject.Inject

class SaveShalawatUseCase @Inject constructor(
    private val repository: ShalawatRepository
) {
    /**
     * Saves a shalawat entry — inserts if id == 0, updates otherwise.
     * Always stamps [Shalawat.updatedAt] with the current time.
     */
    suspend operator fun invoke(shalawat: Shalawat) {
        val timestamped = shalawat.copy(updatedAt = System.currentTimeMillis())
        if (timestamped.id == 0) {
            repository.insert(timestamped.copy(createdAt = System.currentTimeMillis()))
        } else {
            repository.update(timestamped)
        }
    }
}
