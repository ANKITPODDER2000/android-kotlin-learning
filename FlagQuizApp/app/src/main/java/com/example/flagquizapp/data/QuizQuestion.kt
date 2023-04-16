package com.example.flagquizapp.data
import java.io.Serializable

data class QuizQuestion(
    val questionNo: Int,
    val countryImg: String,
    val correctAns: String,
    val options: List<String>,
) : Serializable