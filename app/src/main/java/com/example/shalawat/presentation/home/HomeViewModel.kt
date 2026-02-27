package com.example.shalawat.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.usecase.DeleteShalawatUseCase
import com.example.shalawat.domain.usecase.GetAllShalawatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val shalawatList: List<Shalawat>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllShalawatUseCase: GetAllShalawatUseCase,
    private val deleteShalawatUseCase: DeleteShalawatUseCase
) : ViewModel() {

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = _errorEvent.asSharedFlow()

    val uiState: StateFlow<HomeUiState> = getAllShalawatUseCase()
        .map<List<Shalawat>, HomeUiState> { list ->
            HomeUiState.Success(list)
        }
        .onStart { emit(HomeUiState.Loading) }
        .catch { e ->
            emit(HomeUiState.Error(e.localizedMessage ?: "Failed to load shalawat list"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Loading
        )

    fun deleteShalawat(shalawat: Shalawat) {
        viewModelScope.launch {
            try {
                deleteShalawatUseCase(shalawat)
            } catch (e: Exception) {
                _errorEvent.emit(e.localizedMessage ?: "Failed to delete shalawat")
            }
        }
    }
}
