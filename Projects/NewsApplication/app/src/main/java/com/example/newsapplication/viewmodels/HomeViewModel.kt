package com.example.newsapplication.viewmodels

import android.content.Context
import android.database.Cursor
import androidx.lifecycle.ViewModel
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.db.DBHelper
import com.example.newsapplication.db.NewsContract
import com.example.newsapplication.model.News
import com.example.newsapplication.model.NewsCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val TAG = "HomeViewModel"

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(), HomeContract.Model.ViewModel {

    private lateinit var dbHelper: DBHelper

    override fun initializeDB(context: Context) {
        dbHelper = DBHelper.getInstance(context)
    }

    override suspend fun getNewsFromDB(category: String): NewsCategory? {
        val cursor: Cursor? =
            dbHelper.queryNews("${NewsContract.TopNews.CATEGORY} = ?", arrayListOf(category))
        if (cursor == null || cursor.count == 0) {
            return null
        }

        val allNews = arrayListOf<News>()
        while (cursor.moveToNext()) {
            allNews.add(DBHelper.getNews(cursor))
        }
        cursor.close()
        return NewsCategory(category, allNews)
    }

    override suspend fun insertNewsInDB(title: String, news: List<News>) {
        for (eachNews in news) {
            val values = DBHelper.getNewsContentValues(title, eachNews)
            dbHelper.insertNews(values)
        }
    }

}