package com.example.newsapplication.viewmodels

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.newsapplication.api.ResponseCallBack
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.constants.UtilityConstants
import com.example.newsapplication.model.News
import com.example.newsapplication.model.NewsCategory

class HomeViewModelHandler(
    private val category: String,
    private val downloadListener: HomeContract.Presenter.DownloadListener,
    private val callback: ResponseCallBack,
    private val looper: Looper = Looper.getMainLooper()
) : Handler(looper) {
    private val newsCategoryList = arrayListOf<NewsCategory>()
    override fun handleMessage(msg: Message) {
        when (msg.what) {
            UtilityConstants.NEWS_DOWNLOADED -> {
                val news = NewsCategory(
                    category,
                    callback.getAllNews() as ArrayList<News>
                )
                newsCategoryList.add(news)
                downloadListener.onNewsDownloadFinish(news)
            }
        }
    }
}