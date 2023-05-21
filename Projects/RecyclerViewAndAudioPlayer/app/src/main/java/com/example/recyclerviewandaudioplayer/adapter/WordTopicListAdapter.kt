package com.example.recyclerviewandaudioplayer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewandaudioplayer.R
import com.example.recyclerviewandaudioplayer.databinding.WordTopicListBinding
import com.example.recyclerviewandaudioplayer.viewmodels.Word

class WordTopicListAdapter(val wordTopicList: List<Word>, val onClickPlayMusic: OnClickPlayMusic) :
    RecyclerView.Adapter<WordTopicListAdapter.WordTopicListViewHolder>() {

    interface OnClickPlayMusic {
        fun onClickPlayMusic(position: Int, @RawRes musicRes: Int)
    }

    class WordTopicListViewHolder(val binding: WordTopicListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordTopicListViewHolder {
        val binding =
            WordTopicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordTopicListViewHolder(binding)
    }

    override fun getItemCount(): Int = wordTopicList.size

    override fun onBindViewHolder(holder: WordTopicListViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            onClickPlayMusic.onClickPlayMusic(position, wordTopicList[position].audio)
        }
        holder.binding.apply {
            tvOriginalContent.text = wordTopicList[position].originalWord
            tvTranslatedContent.text = wordTopicList[position].translatedWord
            if (wordTopicList[position].drawableRes != null)
                ivContentImage.setImageResource(
                    wordTopicList[position].drawableRes!!
                )
            else {
                ivContentImage.visibility = View.GONE
                viewImgSep.visibility = View.GONE
            }

            ivPlayPause.setImageResource(
                if (!wordTopicList[position].isPlaying)
                    R.drawable.baseline_play_circle_outline_24
                else
                    R.drawable.baseline_pause_circle_outline_24
            )

        }
    }
}