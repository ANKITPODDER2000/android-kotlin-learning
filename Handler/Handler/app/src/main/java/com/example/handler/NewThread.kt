package com.example.handler

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
import com.example.handler.databinding.ActivityMainBinding

class NewThread(val binding: ActivityMainBinding, val handler: Handler) : HandlerThread("MainThread") {
    var isStopped = false
    override fun run() {
        binding.tvTitle.text = "Start thread..."
        val msg = Message()
        msg.what = 10
        handler.sendMessage(msg)
        var i = 0
        while (true) {
            if (isStopped) return
            if (i % 10 == 0){
                Log.d(
                    "MainActivity",
                    "It's my thread..., Thread name : ${Thread.currentThread().name}, i : $i"
                )
                handler.obtainMessage(i).sendToTarget()
            }
            sleep(1000)
            i++
            val runnable = Runnable { binding.tvCount.text = "Count: $i" }
            handler.post(runnable)
        }
    }
}