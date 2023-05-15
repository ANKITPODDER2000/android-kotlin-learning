package com.example.handler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.handler.databinding.ActivityMainBinding
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var isStopped = false

        val thread = Thread{
            while(true){
                if(isStopped) return@Thread
                Log.d("MainActivity", "It's my thread...")
                Thread.sleep(1000)
            }
        }

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
}