package com.example.flagquizapp

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.flagquizapp.data.QuizQuestion
import com.example.flagquizapp.data.QuizQuestionOptionAdapter
import com.example.flagquizapp.databinding.ActivityQuizQuestionBinding

private val TAG = "QuizQuestionActivity"

class QuizQuestionActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuizQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val question = intent.getSerializableExtra("EXTRA_QUESTION") as QuizQuestion
        binding.pbProgress.progress = question.questionNo
        binding.tvProgress.text = "${question.questionNo} / 10"
        Log.d(TAG, question.countryImg)
        binding.ivFlag.setImageSvgUrl(question.countryImg)

        val adapter = QuizQuestionOptionAdapter(question.options)
        binding.rvOptionList.adapter = adapter
        binding.rvOptionList.layoutManager = LinearLayoutManager(this)
    }
}