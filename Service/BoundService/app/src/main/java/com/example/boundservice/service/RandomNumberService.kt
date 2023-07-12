package com.example.boundservice.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.Random

private const val TAG = "DEBUG_ANKIT"
class RandomNumberService: Service() {
    inner class RandomNumberBinder: Binder() {
        fun getService() = this@RandomNumberService
    }
    private val randomNumberBinder = RandomNumberBinder()
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: is called")
        return randomNumberBinder
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate: is called")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.d(TAG, "onStart: is called")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: is called")
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: is called")
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: is called")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: is called")
    }

    fun getRandomNumber(): Int = Random().nextInt()
}