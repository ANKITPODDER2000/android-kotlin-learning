package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var imgContainer : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.rollBtn)
        imgContainer = findViewById(R.id.imageNum)

        btn.setOnClickListener {
            handleOnClick()
        }
    }

    private fun handleOnClick () {
        var randomVal = ((Math.random() * 100).toInt() % 6) + 1
        imgContainer.setImageResource(
            when(randomVal) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        )
    }
}