package com.example.newsapplication.viewmodels

import androidx.lifecycle.ViewModel
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.model.NewsCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel(), HomeContract.Model.ViewModel {
    override val allNewsCategory: ArrayList<NewsCategory> = arrayListOf()

    override fun insertNewsCategory(newsCategory: NewsCategory) {
        allNewsCategory.add(newsCategory)
    }

}