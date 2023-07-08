package com.example.newsapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapplication.architecture.contracts.HomeContract
import com.example.newsapplication.databinding.NewsCategoryBinding
import com.example.newsapplication.model.NewsCategory

class NewsCategoryAdapter : Adapter<NewsCategoryAdapter.NewsCategoryViewHolder>() {
    private val mNewsCategoryList = arrayListOf<NewsCategory>()
    inner class NewsCategoryViewHolder(val binding: NewsCategoryBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCategoryViewHolder {
        val binding = NewsCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsCategoryViewHolder(binding)
    }

    override fun getItemCount() = mNewsCategoryList.size

    override fun onBindViewHolder(holder: NewsCategoryViewHolder, position: Int) {
        holder.binding.also {
            it.tvNewsCategory.text = mNewsCategoryList[position].title
            val newsCategoryTitleAdapter = NewsCategoryTitleAdapter(mNewsCategoryList[position].news)
            it.rvNewsCategoryTitle.apply {
                adapter = newsCategoryTitleAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    fun updateCategoryList(newsCategoryList: NewsCategory, setAdapter: HomeContract.Presenter.AdapterListener) {
        mNewsCategoryList.add(newsCategoryList)
        this.notifyItemInserted(mNewsCategoryList.size-1)
        setAdapter.onAdapterSetFinish()
    }
}