package com.example.recyclerviewandaudioplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewandaudioplayer.databinding.ActivityMainBinding
import com.example.recyclerviewandaudioplayer.databinding.WordTopicBinding
import com.example.recyclerviewandaudioplayer.viewmodels.WordTopicViewModel

class MainActivity : AppCompatActivity(), WordTopicAdapter.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val wordTopicViewModel = ViewModelProvider(this)[WordTopicViewModel::class.java]

        val wordTopic = wordTopicViewModel.wordTopic
        val wordTopicAdapter = WordTopicAdapter(wordTopic, this)
        binding.rvWordTopicList.adapter = wordTopicAdapter
        binding.rvWordTopicList.layoutManager = LinearLayoutManager(this)

    }

    override fun onClick(title: String) {
        Intent(this, ListActivity::class.java).apply {
            putExtra("EXTRA_TITLE", title)
            startActivity(this)
        }
    }
}