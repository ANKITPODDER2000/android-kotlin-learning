package com.example.learnintent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learnintent.databinding.ActivityThirdBinding

class ThirdActivity: AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        val view: View = binding.root

        setContentView(view)
        binding.btnBack.setOnClickListener { finish() }
    }
}