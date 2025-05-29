package com.molerocn.deckly.data.network

interface AudioPlayerDataSource {
    fun play(url: String)
    fun pause()
    fun stop()
    fun isPlaying(): Boolean
}
