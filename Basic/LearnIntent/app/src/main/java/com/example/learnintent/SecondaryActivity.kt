package com.example.learnintent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnintent.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondaryBinding.inflate(layoutInflater)
        var view: View = binding.root
        setContentView(view)
        Toast.makeText(
            this,
            (intent.getSerializableExtra("PASS_DATA") as PassData).toString(),
            Toast.LENGTH_LONG
        ).show()
        binding.btnNext.setOnClickListener {
            Intent(this, ThirdActivity::class.java).apply {
                startActivity(this)
            }
        }
        binding.btnBack.setOnClickListener { finish() }
    }
}