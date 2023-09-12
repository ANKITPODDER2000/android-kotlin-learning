package com.example.postrequest.api

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface JobApi {
    @POST("users")
    suspend fun sendUserData(@Body user: User?): Response<JobResponse>
}