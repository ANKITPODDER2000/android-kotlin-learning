package com.example.recyclerviewandaudioplayer.activity

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewandaudioplayer.adapter.WordTopicListAdapter
import com.example.recyclerviewandaudioplayer.databinding.ActivityListBinding
import com.example.recyclerviewandaudioplayer.viewmodels.Word
import com.example.recyclerviewandaudioplayer.viewmodels.WordTopicViewModel

class ListActivity : AppCompatActivity(), WordTopicListAdapter.OnClickPlayMusic {

    private lateinit var binding: ActivityListBinding
    private lateinit var wordTopicList: List<Word>
    private lateinit var wordTopicViewModel: WordTopicViewModel
    private lateinit var title: String
    private lateinit var wordTopicListAdapter: WordTopicListAdapter
    @Volatile
    private var prevClickedPosition = -1
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeLayout()

        wordTopicListAdapter = WordTopicListAdapter(wordTopicList, this)
        binding.rvWordTopicList.adapter = wordTopicListAdapter
        binding.rvWordTopicList.layoutManager = LinearLayoutManager(this)
    }

    private fun initializeLayout() {
        title = intent.getStringExtra("EXTRA_TITLE") ?: "All List..."
        binding.tvTitle.text = title
        binding.ivBtnBack.setOnClickListener {
            finish()
        }
        wordTopicViewModel = ViewModelProvider(this)[WordTopicViewModel::class.java]
        wordTopicList = wordTopicViewModel.getWordTopicList(title)
    }

    private fun updateWord(position: Int, isPlaying: Boolean) {
        wordTopicViewModel.updatePlayState(title, position, isPlaying)
        wordTopicListAdapter.notifyItemChanged(position)
    }

    override fun onClickPlayMusic(position: Int, musicRes: Int?) {
        releaseMediaPlayer()
        if (prevClickedPosition != -1 && prevClickedPosition != position) updateWord(prevClickedPosition, false)
        updateWord(position, prevClickedPosition != position)
        prevClickedPosition = if(prevClickedPosition == position) -1 else position

        if(prevClickedPosition != -1 && musicRes != null) {
            Log.d("PlayMusic", "Playing the music");
            mediaPlayer = MediaPlayer.create(this, musicRes)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener {
                onClickPlayMusic(position, null)
            }
        }
    }
    private fun releaseMediaPlayer(){
        if(this::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}