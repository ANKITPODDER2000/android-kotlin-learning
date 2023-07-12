package com.example.newsapplication.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<News>,
)