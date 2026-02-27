package com.example.shalawat.presentation.addedit

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.repository.ShalawatRepository
import com.example.shalawat.domain.usecase.GetShalawatByIdUseCase
import com.example.shalawat.domain.usecase.SaveShalawatUseCase
import com.example.shalawat.presentation.navigation.AddEditRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class AddEditFormState(
    val title: String = "",
    val arabicText: String = "",
    val transliteration: String = "",
    val translation: String = "",
    val audioFileName: String = "",
    val audioSource: String = "",
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val error: String? = null
) {
    val isValid: Boolean get() = title.isNotBlank()
}

@HiltViewModel
class AddEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getShalawatByIdUseCase: GetShalawatByIdUseCase,
    private val saveShalawatUseCase: SaveShalawatUseCase,
    private val repository: ShalawatRepository
) : ViewModel() {

    private val route = savedStateHandle.toRoute<AddEditRoute>()
    private val shalawatId: Int? = route.id

    val isEditMode: Boolean = shalawatId != null

    private var existingShalawat: Shalawat? = null

    private val _formState = MutableStateFlow(AddEditFormState())
    val formState: StateFlow<AddEditFormState> = _formState.asStateFlow()

    init {
        if (isEditMode) {
            loadExistingShalawat()
        }
    }

    private fun loadExistingShalawat() {
        viewModelScope.launch {
            _formState.update { it.copy(isLoading = true, error = null) }
            try {
                val shalawat = getShalawatByIdUseCase(shalawatId!!)
                if (shalawat != null) {
                    existingShalawat = shalawat
                    _formState.update {
                        it.copy(
                            title = shalawat.title,
                            arabicText = shalawat.arabicText,
                            transliteration = shalawat.transliteration,
                            translation = shalawat.translation,
                            audioFileName = shalawat.audioFileName,
                            audioSource = shalawat.audioSource,
                            isLoading = false
                        )
                    }
                } else {
                    _formState.update {
                        it.copy(isLoading = false, error = "Shalawat not found")
                    }
                }
            } catch (e: Exception) {
                _formState.update {
                    it.copy(
                        isLoading = false,
                        error = e.localizedMessage ?: "Failed to load shalawat"
                    )
                }
            }
        }
    }

    fun onTitleChange(value: String) {
        _formState.update { it.copy(title = value) }
    }

    fun onArabicTextChange(value: String) {
        _formState.update { it.copy(arabicText = value) }
    }

    fun onTransliterationChange(value: String) {
        _formState.update { it.copy(transliteration = value) }
    }

    fun onTranslationChange(value: String) {
        _formState.update { it.copy(translation = value) }
    }

    fun clearError() {
        _formState.update { it.copy(error = null) }
    }

    fun onAudioFilePicked(uri: Uri) {
        viewModelScope.launch {
            try {
                val fileName = withContext(Dispatchers.IO) {
                    repository.copyAudioFile(uri)
                }
                _formState.update {
                    it.copy(
                        audioFileName = fileName,
                        audioSource = "internal"
                    )
                }
            } catch (e: Exception) {
                _formState.update {
                    it.copy(error = e.localizedMessage ?: "Failed to import audio file")
                }
            }
        }
    }

    fun save(onComplete: () -> Unit) {
        val state = _formState.value
        if (!state.isValid) return

        viewModelScope.launch {
            _formState.update { it.copy(isSaving = true, error = null) }

            try {
                val shalawat = if (isEditMode && existingShalawat != null) {
                    existingShalawat!!.copy(
                        title = state.title,
                        arabicText = state.arabicText,
                        transliteration = state.transliteration,
                        translation = state.translation,
                        audioFileName = state.audioFileName,
                        audioSource = state.audioSource
                    )
                } else {
                    Shalawat(
                        title = state.title,
                        arabicText = state.arabicText,
                        transliteration = state.transliteration,
                        translation = state.translation,
                        audioFileName = state.audioFileName,
                        audioSource = state.audioSource
                    )
                }

                saveShalawatUseCase(shalawat)
                _formState.update { it.copy(isSaving = false) }
                onComplete()
            } catch (e: Exception) {
                _formState.update {
                    it.copy(
                        isSaving = false,
                        error = e.localizedMessage ?: "Failed to save shalawat"
                    )
                }
            }
        }
    }
}
