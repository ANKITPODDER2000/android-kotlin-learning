package com.example.recyclerviewandaudioplayer.viewmodels

import androidx.lifecycle.ViewModel

class WordTopicViewModel: ViewModel() {
    val wordTopic = listOf("Family", "Color", "Number", "Phase");

    fun getWordTopicList(title: String): List<Word> {
        return when(title) {
            "Family" -> AllWordTopicList.getAllFamily()
            "Color" -> AllWordTopicList.getAllColors()
            "Number" -> AllWordTopicList.getAllNumber()
            "Phase" -> AllWordTopicList.getAllPhase()
            else -> listOf()
        }
    }
}