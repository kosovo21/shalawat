package com.example.shalawat.domain.usecase

import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.repository.ShalawatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllShalawatUseCase @Inject constructor(
    private val repository: ShalawatRepository
) {
    operator fun invoke(): Flow<List<Shalawat>> {
        return repository.getAllShalawat()
    }
}
