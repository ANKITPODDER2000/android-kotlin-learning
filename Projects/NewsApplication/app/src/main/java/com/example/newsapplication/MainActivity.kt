package com.example.newsapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.model.News
import com.example.newsapplication.api.NewsApi
import com.example.newsapplication.api.ResponseCallBack
import com.example.newsapplication.constants.UtilityConstants.Companion.API_KEY
import com.example.newsapplication.constants.UtilityConstants.Companion.NEWS_DOWNLOADED
import com.example.newsapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mNewsApi: NewsApi

    @Inject
    lateinit var mResponseCallBack: ResponseCallBack
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNewsAdapter: NewsAdapter
    private lateinit var mListOfNews: MutableList<News>
    private lateinit var mMainHandler: MainHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mMainHandler = MainHandler(mainLooper)

        setUpNewsAdapter()
        runBlocking { getTopHeadlines() }
    }

    private fun setUpNewsAdapter() {
        mListOfNews = mutableListOf()
        mNewsAdapter = NewsAdapter(mListOfNews)
        binding.rvNewsList.apply {
            adapter = mNewsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private suspend fun getTopHeadlines() {
        withContext(Dispatchers.IO) {
            val response = mNewsApi.getTopHeadLines("us", "business", API_KEY)
            mResponseCallBack.setHandler(mMainHandler)
            response.enqueue(mResponseCallBack)
        }
    }
    inner class MainHandler(looper: Looper): Handler(looper) {
        @SuppressLint("NotifyDataSetChanged")
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                NEWS_DOWNLOADED -> {
                    mListOfNews.addAll(mResponseCallBack.getAllNews())
                    mNewsAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}