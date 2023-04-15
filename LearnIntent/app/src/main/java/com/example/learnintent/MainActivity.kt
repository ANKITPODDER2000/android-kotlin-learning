package com.example.learnintent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.learnintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view: View = binding.root
        setContentView(view)

        binding.btnNext.setOnClickListener{
            Intent(this, SecondaryActivity::class.java).also {
                it.putExtra("PASS_DATA", PassData("Home", 1, true))
                startActivity(it)
            }
        }

        
    }
}