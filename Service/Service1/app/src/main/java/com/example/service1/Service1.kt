package com.example.service1

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.util.Log
import java.util.Random

private const val TAG = "Service1"

class Service1 : Service() {
    private lateinit var serviceLooper: Looper
    private lateinit var serviceHandler: ServiceHandler

    inner class ServiceHandler(looper: Looper) : Handler(looper) {
        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
            Log.d(TAG, "Message is : ${msg.what} | Thread name is : ${Thread.currentThread().name}")
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        HandlerThread("ServiceThread").apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(serviceLooper)
            Log.d(TAG, "Thread name : ${Thread.currentThread().name}")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val randomVal =
            intent?.getIntExtra("EXTRA_RANDOM_VAL", 0).takeIf { it != 0 } ?: Random().nextInt()
        serviceHandler.obtainMessage(randomVal, startId).sendToTarget()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        Log.d(TAG, "onDestroy is called...")
    }

}