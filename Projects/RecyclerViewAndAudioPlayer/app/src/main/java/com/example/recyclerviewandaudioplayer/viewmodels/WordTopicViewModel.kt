package com.example.recyclerviewandaudioplayer.viewmodels

import androidx.lifecycle.ViewModel
import java.text.FieldPosition

class WordTopicViewModel: ViewModel() {
    val wordTopic = listOf("Family", "Color", "Number", "Phase");

    private val familyList = AllWordTopicList.getAllFamily()
    private val colorList = AllWordTopicList.getAllColors()
    private val numberList = AllWordTopicList.getAllNumber()
    private val phaseList = AllWordTopicList.getAllPhase()

    fun getWordTopicList(title: String): List<Word> {
        return when(title) {
            "Family" -> familyList
            "Color" -> colorList
            "Number" -> numberList
            "Phase" -> phaseList
            else -> listOf()
        }
    }

    fun updatePlayState(title: String, position: Int, isPlaying: Boolean) {
        val li = getWordTopicList(title)
        if(position >= li.size) return
        li[position].isPlaying = isPlaying
    }
}