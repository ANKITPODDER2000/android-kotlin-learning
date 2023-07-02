package com.example.newsapplication.architecture.presenter

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.constants.UtilityConstants
import com.example.newsapplication.model.NewsCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomePresenter (
    private val repository: HomeContract.Model.Repository,
    private val model: HomeContract.Model.ViewModel,
    private val view: HomeContract.View,
) : HomeContract.Presenter, HomeContract.Presenter.AdapterListener,
    HomeContract.Presenter.DownloadListener {

    override fun setContentOfHomeScreen(context: Context) {
        view.showProgress()
        view.createAdapter()
        HandlerThread("notUiThread").apply {
            start()
            Handler(looper).postDelayed({
                CoroutineScope(Dispatchers.IO).launch {
                    Log.d("DEBUG_ANKIT", "setContentOfHomeScreen: ${Thread.currentThread().name}")
                    repository.initializeDB(context)
                    repository.getTopNews(
                        this@HomePresenter, UtilityConstants.NEWS_CATEGORY
                    )
                }
            }, 500)
        }
    }

    override fun onAdapterSetFinish() {
        view.hideProgress()
    }

    override fun onAdapterSetError() {}


    override fun onNewsDownloadFinish(newsCategory: NewsCategory, needToUpdateLocalDB: Boolean) {
        runBlocking(Dispatchers.IO) {
            model.insertNewsCategory(newsCategory)
            view.updateAdapterNewsCategory(model.allNewsCategory, this@HomePresenter)
            if(needToUpdateLocalDB) repository.insertNewsInDB(newsCategory.title, newsCategory.news)
        }
    }

    override fun onDataDownloadError() {}
}