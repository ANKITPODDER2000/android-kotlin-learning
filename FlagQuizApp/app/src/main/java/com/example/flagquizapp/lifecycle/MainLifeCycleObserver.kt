package com.example.flagquizapp.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.flagquizapp.util.Security

class MainLifeCycleObserver: LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateEvent() {
        Log.d("LifeCycleObserver", "On Lifecycle Observer")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStopEvent() {
        Log.d("LifeCycleObserver", "On Lifecycle Observer")
        Security.setIsUnlock(false)
    }


}