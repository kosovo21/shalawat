package com.example.shalawat.presentation.detail

import android.content.Context
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.toRoute
import com.example.shalawat.domain.model.Shalawat
import com.example.shalawat.domain.usecase.DeleteShalawatUseCase
import com.example.shalawat.domain.usecase.GetShalawatByIdUseCase
import com.example.shalawat.presentation.navigation.DetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getShalawatByIdUseCase: GetShalawatByIdUseCase,
    private val deleteShalawatUseCase: DeleteShalawatUseCase,
    private val player: ExoPlayer,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val route = savedStateHandle.toRoute<DetailRoute>()
    private val shalawatId: Int = route.id

    private val _shalawat = MutableStateFlow<Shalawat?>(null)
    val shalawat: StateFlow<Shalawat?> = _shalawat.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPosition = MutableStateFlow(0L)
    val currentPosition: StateFlow<Long> = _currentPosition.asStateFlow()

    private val _duration = MutableStateFlow(0L)
    val duration: StateFlow<Long> = _duration.asStateFlow()

    private var positionPollingJob: Job? = null

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _isPlaying.value = isPlaying
            if (isPlaying) {
                startPositionPolling()
            } else {
                stopPositionPolling()
                _currentPosition.value = player.currentPosition
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            if (playbackState == Player.STATE_READY) {
                _duration.value = player.duration.coerceAtLeast(0L)
            }
            if (playbackState == Player.STATE_ENDED) {
                _isPlaying.value = false
                _currentPosition.value = 0L
                player.seekTo(0)
                player.pause()
            }
        }
    }

    init {
        player.addListener(playerListener)
        loadShalawat()
    }

    private fun loadShalawat() {
        viewModelScope.launch {
            val result = getShalawatByIdUseCase(shalawatId)
            _shalawat.value = result
            result?.let { preparePlayer(it) }
        }
    }

    private fun preparePlayer(shalawat: Shalawat) {
        val mediaItem = when (shalawat.audioSource) {
            "asset" -> {
                val assetUri = Uri.parse("asset:///audio/${shalawat.audioFileName}")
                MediaItem.fromUri(assetUri)
            }
            "internal" -> {
                val file = File(context.filesDir, "audio/${shalawat.audioFileName}")
                MediaItem.fromUri(Uri.fromFile(file))
            }
            else -> return
        }

        player.stop()
        player.clearMediaItems()
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    fun play() {
        player.play()
    }

    fun pause() {
        player.pause()
    }

    fun stop() {
        player.stop()
        player.seekTo(0)
        player.prepare()
        _currentPosition.value = 0L
        _isPlaying.value = false
    }

    fun seekTo(position: Long) {
        player.seekTo(position)
        _currentPosition.value = position
    }

    fun deleteShalawat(onDeleted: () -> Unit) {
        viewModelScope.launch {
            _shalawat.value?.let {
                deleteShalawatUseCase(it)
                onDeleted()
            }
        }
    }

    private fun startPositionPolling() {
        stopPositionPolling()
        positionPollingJob = viewModelScope.launch {
            while (true) {
                _currentPosition.value = player.currentPosition
                delay(200L)
            }
        }
    }

    private fun stopPositionPolling() {
        positionPollingJob?.cancel()
        positionPollingJob = null
    }

    override fun onCleared() {
        super.onCleared()
        player.removeListener(playerListener)
        player.stop()
        player.clearMediaItems()
        stopPositionPolling()
    }
}
