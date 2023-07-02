package com.example.newsapplication.viewmodels

import com.example.newsapplication.api.NewsApi
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.constants.UtilityConstants
import com.example.newsapplication.model.News
import com.example.newsapplication.model.NewsCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val TAG = "HomeRepository"

class HomeRepository @Inject constructor(
    private val newsApi: NewsApi,
) : HomeContract.Model.Repository {
    private lateinit var viewModel: HomeViewModel
    private val allNewsCategory = arrayListOf<NewsCategory>()
    override fun getTopNews() = allNewsCategory
    override suspend fun getNewsFromAPI(category: String): NewsCategory? {
        val newsCategoryScope = CoroutineScope(Dispatchers.IO).async {
            var newsCategory: NewsCategory? = null
            val response = newsApi.getTopHeadLines("in", category, UtilityConstants.API_KEY, 3)
            if (response.isSuccessful) {
                response.body()?.let {
                    newsCategory = NewsCategory(category, it.articles as ArrayList<News>)
                }
            }
            newsCategory
        }
        return newsCategoryScope.await()
    }

    override suspend fun fetchTopNews(
        downloadListener: HomeContract.Presenter.DownloadListener,
        categories: ArrayList<String>,
    ) {
        runBlocking(Dispatchers.IO) {
            for (category in categories) {
                val newsCategory = viewModel.getNewsFromDB(category) ?: getNewsFromAPI(category)
                newsCategory?.run {
                    allNewsCategory.add(this)
                    downloadListener.onNewsDownloadFinish(this)
                }
            }
        }
    }

    override fun setViewModel(model: HomeContract.Model.ViewModel) {
        viewModel = model as HomeViewModel
    }
}