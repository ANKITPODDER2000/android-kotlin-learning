package com.example.newsapplication.architecture.presenter

import android.content.Context
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.constants.UtilityConstants
import com.example.newsapplication.model.NewsCategory
import kotlinx.coroutines.Dispatchers
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
        runBlocking(Dispatchers.IO) {
            repository.initializeDB(context)
            repository.getTopNews(
                this@HomePresenter, UtilityConstants.NEWS_CATEGORY
            )
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