package com.example.todoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {
    lateinit var binding: ActivityEntryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = this.getPreferences(Context.MODE_PRIVATE)
        val username: String? = preference.getString("USER_NAME", null)

        if (username != null)
            Intent(this, MainActivity::class.java).also { startActivity(it) }
        else
            Intent(this, WelcomeActivity::class.java).also { startActivity(it) }
        finish()
    }
}