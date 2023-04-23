package com.example.flagquizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.flagquizapp.data.QuizQuestion
import com.example.flagquizapp.data.QuizQuestionOptionAdapter
import com.example.flagquizapp.data.getQuestion
import com.example.flagquizapp.databinding.ActivityQuizQuestionBinding
import com.example.flagquizapp.util.Security
import com.squareup.picasso.Picasso

private val TAG = "QuizQuestionActivity"

class QuizQuestionActivity : AppCompatActivity() {

    private var correctAnsByUser: Int = 0
    var currentSelectedPosition: Int = NO_POSITION
    lateinit var binding: ActivityQuizQuestionBinding
    lateinit var question: QuizQuestion
    var options: MutableList<MutableList<String>> = mutableListOf()
    var correctAns: String? = null
    var questionNo = 1
    @SuppressLint("SetTextI18n")
    fun setData() {
        question = getQuestion(questionNo)
        options.clear()
        options.addAll(question.options.shuffled())

        currentSelectedPosition = NO_POSITION

        binding.pbProgress.progress = question.questionNo
        binding.tvProgress.text = "${question.questionNo} / 10"
        val imageUrl = question.countryImg
        Log.d(TAG, imageUrl)
        if (imageUrl.endsWith(".png")) {
            Picasso.get().load(imageUrl).into(binding.ivFlagPng)
            binding.ivFlagPng.visibility = View.VISIBLE
            binding.ivFlag.visibility = View.GONE
        } else {
            binding.ivFlag.setImageSvgUrl(imageUrl)
            binding.ivFlagPng.visibility = View.GONE
            binding.ivFlag.visibility = View.VISIBLE
        }


        correctAns = null
        binding.btnCheckAns.text = "Check Answer"
        questionNo += 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val updatePosition: (Int) -> Unit = { pos: Int ->
            currentSelectedPosition = pos
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setData()

        val adapter = QuizQuestionOptionAdapter(options, updatePosition)
        binding.rvOptionList.adapter = adapter
        binding.rvOptionList.layoutManager = LinearLayoutManager(this)


        binding.btnCheckAns.setOnClickListener {
            if(binding.btnCheckAns.text == "See Score") {
                Intent(this, ScoreActivity::class.java).also {
                    val user_name = intent.getStringExtra("EXTRA_USER_NAME")
                    it.putExtra("EXTRA_USER_NAME", user_name)
                    it.putExtra("EXTRA_SCORE", correctAnsByUser.toString())
                    startActivity(it)
                    finish()
                }
            }
            if (currentSelectedPosition == NO_POSITION) {
                Toast.makeText(this, "Please Select one option", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (correctAns == null) {
                var pos1 = -1
                var pos2 = -1
                for (i in 0..3) {
                    if (options[i][0] == question.correctAns || i == currentSelectedPosition) {
                        options[i][1] = question.correctAns

                        if(pos1 == -1) pos1 = i
                        else if(pos2 == -1) pos2 = i
                    }
                }
                correctAns = question.correctAns
                // Update no of Correct ans.
                if(correctAns == options[currentSelectedPosition][0]) correctAnsByUser += 1

                // Update Adapter to show updated data
                adapter.notifyItemChanged(pos1)
                if(pos2 != -1) adapter.notifyItemChanged(pos2)

                // Update btn text based on Scenario.
                binding.btnCheckAns.text = if(questionNo == 11) "See Score" else "Go to Next Question"
            } else {
                setData()
                Log.d(TAG, options.toString())
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(!Security.isUnlock) Intent(this, SetPinActivity::class.java).also { startActivityForResult(it, 2) }
    }

    override fun onPause() {
        super.onPause()
        Security.setIsUnlock(false)
    }
}