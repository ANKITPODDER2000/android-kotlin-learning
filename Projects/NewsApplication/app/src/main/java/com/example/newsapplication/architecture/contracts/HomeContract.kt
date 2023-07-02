package com.example.newsapplication.architecture.contracts

import android.content.Context
import com.example.newsapplication.model.News
import com.example.newsapplication.model.NewsCategory

interface HomeContract {
    interface Model {
        interface ViewModel {
            val allNewsCategory: ArrayList<NewsCategory>
            fun insertNewsCategory(newsCategory: NewsCategory)
        }
        interface Repository {
            fun initializeDB(context: Context)
            suspend fun getNewsFromAPI(downloadListener: Presenter.DownloadListener, category: String)
            suspend fun getNewsFromDB(downloadListener: Presenter.DownloadListener, category: String): Boolean
            suspend fun insertNewsInDB(title: String, news: List<News>)
            suspend fun isNewsAvailableInDB(downloadListener: Presenter.DownloadListener, category: String): Boolean
            suspend fun getTopNews(downloadListener: Presenter.DownloadListener, categories: ArrayList<String>)
        }
    }

    interface View {
        fun showProgress()
        fun hideProgress()
        fun createAdapter()
        fun updateAdapterNewsCategory(newsCategory: ArrayList<NewsCategory>, setAdapter: Presenter.AdapterListener)
    }

    interface Presenter {
        interface DownloadListener {
            // fun onDataDownloadFinish(newsCategory: ArrayList<NewsCategory>)
            fun onNewsDownloadFinish(newsCategory: NewsCategory, needToUpdateLocalDB: Boolean = true)
            fun onDataDownloadError()
        }
        interface AdapterListener {
            fun onAdapterSetFinish()
            fun onAdapterSetError()
        }
        fun setContentOfHomeScreen(context: Context)
    }
}