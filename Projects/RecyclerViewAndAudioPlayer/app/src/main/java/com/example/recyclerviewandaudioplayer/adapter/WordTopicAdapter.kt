package com.example.recyclerviewandaudioplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewandaudioplayer.databinding.WordTopicBinding

class WordTopicAdapter(val wordTopicList: List<String>, val onClickListener: OnClickListener) : RecyclerView.Adapter<WordTopicAdapter.WordTopicViewHolder>() {

    class WordTopicViewHolder(val binding: WordTopicBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnClickListener {
        fun onClick(title: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordTopicViewHolder {
        val binding = WordTopicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordTopicViewHolder(binding)
    }

    override fun getItemCount(): Int = wordTopicList.size

    override fun onBindViewHolder(holder: WordTopicViewHolder, position: Int) {
        holder.binding.tvTitle.text = wordTopicList[position]
        holder.binding.root.setOnClickListener {
            onClickListener.onClick(wordTopicList[position])
        }
    }
}