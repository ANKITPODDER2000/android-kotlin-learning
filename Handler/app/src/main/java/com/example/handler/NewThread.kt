package com.example.handler

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.handler.databinding.ActivityMainBinding

class NewThread(val binding: ActivityMainBinding, val handler: Handler) : Thread() {
    var isStopped = false
    override fun run() {
        binding.tvTitle.text = "Start thread..."
        var i = 0
        while (true) {
            if (isStopped) return
            Log.d(
                "MainActivity",
                "It's my thread..., Thread name : ${Thread.currentThread().name}"
            )
            Thread.sleep(1000)
            i++
            val runnable = Runnable { binding.tvCount.text = "Count: $i" }
            handler.post(runnable)
        }
    }
}