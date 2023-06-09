package com.example.handler

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.handler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val handler = NewHandler(this as Context)
        var thread = NewThread(binding, handler)

        binding.btnStart.setOnClickListener {
            try {
                thread.start()
            } catch (_: Exception) {
                binding.tvTitle.text = "Error while starting Thread..."
            }

        }
        binding.btnStop.setOnClickListener {
            thread.isStopped = true
            binding.tvTitle.text = "Stop thread..."
        }
    }

    /*Still we are not in the UI thread, but still we can change the UI, this is Perfect example of memory leakage...
    private fun getThread(): Thread {
        return Thread {
            var i = 0
            while (true) {
                if (isStopped) return@Thread
                Log.d(
                    "MainActivity",
                    "It's my thread..., Thread name : ${Thread.currentThread().name}"
                )
                Thread.sleep(1000)
                i++
                // binding.tvCount.text = "Count: $i"
                findViewById<TextView>(R.id.tvCount).text = "Count: $i"
            }
        }
    }
    */
}