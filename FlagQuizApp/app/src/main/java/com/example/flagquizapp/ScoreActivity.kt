package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flagquizapp.databinding.ActivityScoreBinding
import com.example.flagquizapp.util.Security

class ScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = intent.getStringExtra("EXTRA_USER_NAME")
        binding.tvScore.text = "Score is ${intent.getStringExtra("EXTRA_SCORE")} / 10"
    }

    override fun onResume() {
        super.onResume()
        if (!Security.isUnlock) Intent(
            this,
            SetPinActivity::class.java
        ).also { startActivityForResult(it, 2) }
    }

    override fun onPause() {
        super.onPause()
        Security.setIsUnlock(false)
    }
}