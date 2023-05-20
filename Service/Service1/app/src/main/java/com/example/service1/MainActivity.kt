package com.example.service1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.service1.databinding.ActivityMainBinding
import java.util.Random

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            Intent(this, Service1::class.java).also {
                val randomNum = Random().nextInt()
                Log.d(TAG, "MainActivity passing the random value : $randomNum")
                it.putExtra("EXTRA_RANDOM_VAL", randomNum)
                startService(it)
            }
        }

        binding.btnStop.setOnClickListener {
            Intent(this, Service1::class.java).also {
                stopService(it)
            }
        }
    }
}