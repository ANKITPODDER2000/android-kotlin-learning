package com.example.recyclerviewandaudioplayer.viewmodels

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Word(
    val originalWord: String,
    val translatedWord: String,
    @DrawableRes val drawableRes: Int?,
    @RawRes val audio: Int,
    var isPlaying: Boolean = false
)