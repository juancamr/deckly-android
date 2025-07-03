package com.molerocn.deckly.presentation

import android.widget.Toast

data class UiMessage(
    val shouldShow: Boolean,
    val message: String = "",
    val duration: Int = Toast.LENGTH_SHORT
)

