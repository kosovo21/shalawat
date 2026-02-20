package com.example.shalawat.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.usecase.DeleteShalawatUseCase
import com.example.shalawat.domain.usecase.GetAllShalawatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllShalawatUseCase: GetAllShalawatUseCase,
    private val deleteShalawatUseCase: DeleteShalawatUseCase
) : ViewModel() {

    val shalawatList: StateFlow<List<Shalawat>> = getAllShalawatUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun deleteShalawat(shalawat: Shalawat) {
        viewModelScope.launch {
            deleteShalawatUseCase(shalawat)
        }
    }
}
