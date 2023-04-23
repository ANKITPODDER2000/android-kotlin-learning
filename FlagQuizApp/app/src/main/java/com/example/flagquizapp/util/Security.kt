package com.example.flagquizapp.util

object Security {
    private var _isUnlock: Boolean = false
    val isUnlock: Boolean
        get() = _isUnlock

    fun setIsUnlock(new_val: Boolean) {
        _isUnlock = new_val
    }
}