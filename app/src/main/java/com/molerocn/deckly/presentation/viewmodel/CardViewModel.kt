package com.molerocn.deckly.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.molerocn.deckly.domain.usecase.MediaPlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val playAudio: MediaPlayerUseCase.PlayAudioUseCase,
    private val pauseAudio: MediaPlayerUseCase.PauseAudioUseCase,
    private val stopAudio: MediaPlayerUseCase.StopAudioUseCase
) : ViewModel() {

    fun onCreate() {
    }

    fun play(url: String) = playAudio(url)

    fun pause() = pauseAudio()

    fun stop() = stopAudio()
}