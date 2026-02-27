package com.example.shalawat.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.repository.ShalawatRepository
import com.example.shalawat.domain.usecase.DeleteShalawatUseCase
import com.example.shalawat.domain.usecase.GetAllShalawatUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
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

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllShalawatUseCase: GetAllShalawatUseCase,
    private val deleteShalawatUseCase: DeleteShalawatUseCase,
    private val repository: ShalawatRepository
) : ViewModel() {

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent: SharedFlow<String> = _errorEvent.asSharedFlow()

    // Search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Selected category filter
    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    // Available categories
    val categories = listOf("All", "Daily", "Popular", "Healing", "Protection")

    // Combined flow: search + category filter
    val uiState: StateFlow<HomeUiState> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                getAllShalawatUseCase()
            } else {
                repository.searchShalawat(query)
            }
        }
        .combine(_selectedCategory) { list, category ->
            if (category == "All") {
                list
            } else {
                list.filter { it.category.equals(category, ignoreCase = true) }
            }
        }
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

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String) {
        _selectedCategory.value = category
    }

    fun toggleFavorite(shalawat: Shalawat) {
        viewModelScope.launch {
            try {
                repository.toggleFavorite(shalawat.id, !shalawat.isFavorite)
            } catch (e: Exception) {
                _errorEvent.emit(e.localizedMessage ?: "Failed to update favorite")
            }
        }
    }

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
