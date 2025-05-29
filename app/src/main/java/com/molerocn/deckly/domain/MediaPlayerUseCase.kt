package com.molerocn.deckly.domain

import com.molerocn.deckly.data.network.AudioPlayerDataSourceImpl
import javax.inject.Inject

class MediaPlayerUseCase {
    class PlayAudioUseCase @Inject constructor(private val player: AudioPlayerDataSourceImpl) {
        operator fun invoke(url: String) = player.play(url)
    }

    class PauseAudioUseCase @Inject constructor(private val player: AudioPlayerDataSourceImpl) {
        operator fun invoke() = player.pause()
    }

    class StopAudioUseCase @Inject constructor(private val player: AudioPlayerDataSourceImpl) {
        operator fun invoke() = player.stop()
    }

}