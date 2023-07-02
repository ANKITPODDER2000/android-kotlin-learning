package com.example.newsapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapplication.databinding.NewsCategoryTitleBinding
import com.example.newsapplication.model.News

class NewsCategoryTitleAdapter(private val newsList: List<News>) :
    Adapter<NewsCategoryTitleAdapter.NewsCategoryTitleViewHolder>() {
    inner class NewsCategoryTitleViewHolder(val binding: NewsCategoryTitleBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsCategoryTitleViewHolder {
        val binding = NewsCategoryTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsCategoryTitleViewHolder(binding)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsCategoryTitleViewHolder, position: Int) {
        holder.binding.tvNewsCategoryTitle.text = newsList[position].title
    }
}