package com.example.newsapplication

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.adapter.NewsCategoryAdapter
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.architecture.presenter.HomePresenter
import com.example.newsapplication.databinding.ActivityHomeBinding
import com.example.newsapplication.model.NewsCategory
import com.example.newsapplication.viewmodels.HomeRepository
import com.example.newsapplication.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeContract.View {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: NewsCategoryAdapter
    private lateinit var presenter: HomePresenter
    private val viewModel: HomeViewModel by viewModels()
    @Inject lateinit var homeRepository: HomeRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgress()
        initializeActivity()
    }

    private fun initializeActivity() {
        presenter = HomePresenter(homeRepository, viewModel, this)
    }

    override fun onResume() {
        super.onResume()
        presenter.setContentOfHomeScreen(this)
    }

    override fun showProgress() {
        binding.pbNewsCategory.visibility = View.VISIBLE
        binding.rvNewsCategory.visibility = View.GONE
    }

    override fun createAdapter() {
        adapter = NewsCategoryAdapter()
        binding.rvNewsCategory.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun hideProgress() {
        binding.pbNewsCategory.visibility = View.GONE
        binding.rvNewsCategory.visibility = View.VISIBLE
    }

    override fun updateAdapterNewsCategory(
        newsCategory: ArrayList<NewsCategory>,
        setAdapter: HomeContract.Presenter.AdapterListener,
    ) {
        runOnUiThread {
            adapter.updateCategoryList(newsCategory, setAdapter)
        }
    }
}