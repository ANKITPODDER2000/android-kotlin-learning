package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.flagquizapp.data.getQuestion
import com.example.flagquizapp.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nextActivityIntent: Intent = Intent(this, QuizQuestionActivity::class.java)
        binding.btnStart.setOnClickListener {
            if(binding.etUserName.text.toString().isEmpty()) {
                Toast.makeText(this, "Please give user name..", Toast.LENGTH_SHORT).show()
            }
            else {
                nextActivityIntent.also {
                    val question = getQuestion(1)
                    Log.d(TAG, question.toString())
                    it.putExtra("EXTRA_QUESTION",question)
                    startActivity(it)
                }
            }
        }
    }
}