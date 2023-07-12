package com.example.newsapplication.viewmodels

import android.util.Log
import com.example.newsapplication.api.NewsApi
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.constants.UtilityConstants
import com.example.newsapplication.model.News
import com.example.newsapplication.model.NewsCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.math.log


class HomeRepository @Inject constructor(
    private val newsApi: NewsApi,
) : HomeContract.Model.Repository {
    private lateinit var viewModel: HomeViewModel
    private val allNewsCategory = arrayListOf<NewsCategory>()
    private val _dataFlow = MutableSharedFlow<NewsCategory>()
    val dataFlow: Flow<NewsCategory>
        get() = _dataFlow

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
        val newsCategory = newsCategoryScope.await()
        Log.d("DEBUG_ANKIT", "getNewsFromAPI: Value of newsCategory : ${newsCategory.toString()}")
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("DEBUG_ANKIT", "getNewsFromAPI: Value of newsCategory : ${newsCategory.toString()}")
            newsCategory?.apply {
                viewModel.insertNewsInDB(
                    title,
                    news
                )
            }
        }
        return newsCategory
    }

    override suspend fun fetchTopNews(
        downloadListener: HomeContract.Presenter.DownloadListener,
        categories: ArrayList<String>,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            for (category in categories) {
                val newsCategory = viewModel.getNewsFromDB(category) ?: getNewsFromAPI(category)
                newsCategory?.run {
                    _dataFlow.emit(this)
                    val newNewsCategory = getNewsFromAPI(category)
                    if(newNewsCategory != null) _dataFlow.emit(newNewsCategory)
                }
            }
        }
    }

    override fun setViewModel(model: HomeContract.Model.ViewModel) {
        viewModel = model as HomeViewModel
    }
}