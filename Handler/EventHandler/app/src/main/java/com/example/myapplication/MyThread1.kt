package com.example.myapplication

import android.os.Handler
import android.os.Looper
import android.util.Log


class MyThread1: Thread() {
    lateinit var handler: Handler
    override fun run() {
        Looper.prepare()
        sleep(6000)
        handler = Handler()
        Log.d(TAG, "Start the thread....")
        Looper.loop()
    }

    override fun destroy() {
        Log.d(TAG, "Destroy the thread....")
    }
}