package com.molerocn.deckly.data.network

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AudioPlayerDataSourceImpl @Inject constructor() : AudioPlayerDataSource {
    private var mediaPlayer: MediaPlayer? = null

    override fun play(url: String) {
        stop()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            )
            setDataSource(url)
            Log.i("url", url)
            setOnPreparedListener { it.start() }
            prepareAsync()
        }
    }

    override fun pause() {
        mediaPlayer?.takeIf { it.isPlaying }?.pause()
    }

    override fun stop() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }
}