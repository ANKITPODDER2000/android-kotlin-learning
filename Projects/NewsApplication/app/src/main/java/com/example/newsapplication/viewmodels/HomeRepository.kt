package com.example.newsapplication.viewmodels

import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.newsapplication.api.NewsApi
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.constants.UtilityConstants
import com.example.newsapplication.db.DBHelper
import com.example.newsapplication.db.NewsContract
import com.example.newsapplication.model.News
import com.example.newsapplication.model.NewsCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val TAG = "HomeRepository"

class HomeRepository @Inject constructor(
    private val newsApi: NewsApi,
) : HomeContract.Model.Repository {

    private lateinit var dbHelper: DBHelper

    override fun initializeDB(context: Context) {
        dbHelper = DBHelper.getInstance(context)
    }

    override suspend fun getNewsFromAPI(
        downloadListener: HomeContract.Presenter.DownloadListener,
        category: String,
    ) {
        // After getting the data from API, it will send the data to Presenter, and Presenter can store data in ViewModel directly
        runBlocking(Dispatchers.IO) {
            val response = newsApi.getTopHeadLines("in", category, UtilityConstants.API_KEY, 3)
            if (response.isSuccessful) {
                val responseBody = response.body()
                responseBody?.let {
                    downloadListener.onNewsDownloadFinish(NewsCategory(category, responseBody.articles as ArrayList<News>))
                }
            }
        }
    }

    override suspend fun getNewsFromDB(
        downloadListener: HomeContract.Presenter.DownloadListener,
        category: String,
    ): Boolean {
        val cursor: Cursor? =
            dbHelper.queryNews("${NewsContract.TopNews.CATEGORY} = ?", arrayListOf(category))
        if (cursor == null || cursor.count == 0) {
            downloadListener.onDataDownloadError()
            return false
        }
        Log.d(TAG, "getNewsFromDB: for category : $category cursor count : ${cursor.count}")

        val allNews = arrayListOf<News>()
        while (cursor.moveToNext()) {
            allNews.add(DBHelper.getNews(cursor))
        }
        Log.d(TAG, "getNewsFromDB: for category : $category cursor count 2 : ${cursor.count}")
        cursor.close()
        val newsCategory = NewsCategory(category, allNews)
        downloadListener.onNewsDownloadFinish(newsCategory, false)
        return true
    }

    override suspend fun insertNewsInDB(title: String, news: List<News>) {
        for (eachNews in news) {
            val values = DBHelper.getNewsContentValues(title, eachNews)
            dbHelper.insertNews(values)
        }
    }

    override suspend fun isNewsAvailableInDB(
        downloadListener: HomeContract.Presenter.DownloadListener,
        category: String,
    ): Boolean {
        return getNewsFromDB(downloadListener, category)
    }

    override suspend fun getTopNews(
        downloadListener: HomeContract.Presenter.DownloadListener,
        categories: ArrayList<String>
    ) {
        runBlocking(Dispatchers.IO) {
            for(category in categories) {
                if(!isNewsAvailableInDB(downloadListener, category)) getNewsFromAPI(downloadListener, category)
            }
        }
    }
}