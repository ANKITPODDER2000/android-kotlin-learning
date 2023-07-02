package com.example.newsapplication.architecture.contracts

import android.content.Context
import com.example.newsapplication.model.News
import com.example.newsapplication.model.NewsCategory

interface HomeContract {
    interface Model {
        interface ViewModel {
            fun initializeDB(context: Context)
            suspend fun getNewsFromDB(category: String): NewsCategory?
            suspend fun insertNewsInDB(title: String, news: List<News>)

        }
        interface Repository {
            fun getTopNews(): ArrayList<NewsCategory>
            suspend fun getNewsFromAPI(category: String): NewsCategory?
            suspend fun fetchTopNews(downloadListener: Presenter.DownloadListener, categories: ArrayList<String>)
            fun setViewModel(model: ViewModel)
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