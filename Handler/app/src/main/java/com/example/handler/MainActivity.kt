package com.example.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.handler.databinding.ActivityMainBinding
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var isStopped = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var thread = getThread()

        binding.btnStart.setOnClickListener {
            try {
                thread.start()
                binding.tvTitle.text = "Start thread..."
            } catch (_: Exception) {
                binding.tvTitle.text = "Error while starting Thread..."
            }

        }
        binding.btnStop.setOnClickListener {
            isStopped = true
            binding.tvTitle.text = "Stop thread..."
        }
    }

    /*Still we are not in the UI thread, but still we can change the UI, this is Perfect example of memory leakage...*/
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
}