package com.example.recyclerviewandaudioplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewandaudioplayer.databinding.WordTopicListBinding
import com.example.recyclerviewandaudioplayer.viewmodels.Word

class WordTopicListAdapter(val wordTopicList: List<Word>) :
    RecyclerView.Adapter<WordTopicListAdapter.WordTopicListViewHolder>() {

    class WordTopicListViewHolder(val binding: WordTopicListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordTopicListViewHolder {
        val binding =
            WordTopicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordTopicListViewHolder(binding)
    }

    override fun getItemCount(): Int = wordTopicList.size

    override fun onBindViewHolder(holder: WordTopicListViewHolder, position: Int) {
        holder.binding.apply {
            tvOriginalContent.text = wordTopicList[position].originalWord
            tvTranslatedContent.text = wordTopicList[position].translatedWord
            if (wordTopicList[position].drawableRes != null)
                ivContentImage.setImageResource(
                    wordTopicList[position].drawableRes!!
                )
            else{
                ivContentImage.visibility = View.GONE
                viewImgSep.visibility = View.GONE
            }
        }
    }
}