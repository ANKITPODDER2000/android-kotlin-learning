package com.example.newsapplication.api

import android.os.Handler
import android.util.Log
import com.example.newsapplication.constants.UtilityConstants
import com.example.newsapplication.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ResponseCallBack @Inject constructor() : Callback<ApiResponse> {
    private var mResponseBody: ApiResponse? = null
    private lateinit var mHandler: Handler

    fun setHandler(handler: Handler) {
        mHandler = handler
    }
    override fun onResponse(
        call: Call<ApiResponse>?,
        response: Response<ApiResponse>?,
    ) {
        mResponseBody = response?.body()
        mHandler.obtainMessage(UtilityConstants.NEWS_DOWNLOADED).sendToTarget()
    }

    override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
        Log.d(UtilityConstants.MAIN_TAG, "Error to get Response...")
    }

    fun getAllNews() = (mResponseBody?.articles).takeIf { it != null } ?: listOf()
}