package com.example.recyclerviewandaudioplayer.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewandaudioplayer.adapter.WordTopicListAdapter
import com.example.recyclerviewandaudioplayer.databinding.ActivityListBinding
import com.example.recyclerviewandaudioplayer.viewmodels.Word
import com.example.recyclerviewandaudioplayer.viewmodels.WordTopicViewModel

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var wordTopicList: List<Word>
    private lateinit var wordTopicViewModel: WordTopicViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeLayout()

        binding.rvWordTopicList.adapter = WordTopicListAdapter(wordTopicList)
        binding.rvWordTopicList.layoutManager = LinearLayoutManager(this)


    }

    private fun initializeLayout() {
        val title = intent.getStringExtra("EXTRA_TITLE")
        binding.tvTitle.text = title ?: "All List..."
        binding.ivBtnBack.setOnClickListener{
            finish()
        }
        wordTopicViewModel = ViewModelProvider(this)[WordTopicViewModel::class.java]
        wordTopicList = if(title != null) wordTopicViewModel.getWordTopicList(title) else listOf()
    }
}