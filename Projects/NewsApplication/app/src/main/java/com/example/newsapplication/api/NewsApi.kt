package com.example.newsapplication.api

import com.example.newsapplication.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadLines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int = 20
    ): Call<ApiResponse>

}