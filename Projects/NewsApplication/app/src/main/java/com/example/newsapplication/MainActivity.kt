package com.example.newsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.api.NewsApi
import com.example.newsapplication.constants.UtilityConstants.Companion.API_KEY
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.model.News
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mNewsApi: NewsApi

    private lateinit var binding: ActivityMainBinding
    private lateinit var mNewsAdapter: NewsAdapter
    private lateinit var mListOfNews: MutableList<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


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
        }
    }
}