package com.example.shalawat.domain.usecase

import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.repository.ShalawatRepository
import javax.inject.Inject

class GetShalawatByIdUseCase @Inject constructor(
    private val repository: ShalawatRepository
) {
    suspend operator fun invoke(id: Int): Shalawat? {
        return repository.getShalawatById(id)
    }
}
