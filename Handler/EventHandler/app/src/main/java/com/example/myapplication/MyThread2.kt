package com.example.myapplication

import android.os.Handler
import android.os.HandlerThread
import android.util.Log

class MyThread2: HandlerThread("MyThread") {
    lateinit var handler: Handler
    var isThreadReady = false

    override fun onLooperPrepared() {
        Thread.sleep(3000)
        handler = Handler(this.looper)
        isThreadReady = true
    }

    override fun destroy() {
        Log.d(TAG, "Destroy thread2...")
    }
}