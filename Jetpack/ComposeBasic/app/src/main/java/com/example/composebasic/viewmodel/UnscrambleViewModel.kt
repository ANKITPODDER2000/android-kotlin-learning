package com.example.composebasic.viewmodel

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.example.composebasic.module.GameState
import com.example.composebasic.util.AllWord
import com.example.composebasic.util.ButtonType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UnscrambleViewModel : ViewModel() {
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState>
        get() = _gameState.asStateFlow()

    init {
        initNewGame()
    }

    fun initNewGame() {
        _gameState.update {
            it.copy(
                currentScore = 0,
                currentQuestionNo = 0,
                userInput = "",
                isGameOver = false,
                usedWords = hashSetOf(),
                currentScrambleWord = "",
                isSubmitted = false
            )
        }
        updateCurrentRandomWord(getRandomWord())
    }

    @VisibleForTesting
    fun updateCurrentRandomWord(word: String, isSkip: Boolean = false) {
        if (_gameState.value.isGameOver) return
        val usedWords = _gameState.value.usedWords
        usedWords.add(word)
        val currentQuestionNo = _gameState.value.currentQuestionNo
        _gameState.update {
            it.copy(
                isSubmitted = false,
                userInput = "",
                isWrongInput = false,
                currentWord = word,
                currentQuestionNo = currentQuestionNo + if (isSkip) 0 else 1,
                currentScrambleWord = getShuffledWord(word),
                usedWords = usedWords
            )
        }
    }

    private fun getRandomWord(): String {
        var randomWord = AllWord.getRandomWord()
        while (_gameState.value.usedWords.contains(randomWord)) {
            randomWord = AllWord.getRandomWord()
        }
        return randomWord
    }

    private fun getShuffledWord(word: String): String {
        if(word.length == 1) return word
        val shWord = word.toCharArray()
        shWord.shuffle()
        while (word == String(shWord)) {
            shWord.shuffle()
        }
        return String(shWord)
    }

    fun userInputChangeHandler(input: String) {
        val currentWord = _gameState.value.currentWord
        val wordMap = hashMapOf<Char, Int>()
        var isWrongInput = false
        for (ch in currentWord) {
            if (ch !in wordMap.keys) wordMap[ch] = 1
            else wordMap[ch] = (wordMap[ch] ?: 0) + 1
        }
        for (ch in input) {
            if (ch !in wordMap.keys || (wordMap[ch] ?: 0) <= 0) {
                isWrongInput = true
                break
            }
            wordMap[ch] = (wordMap[ch] ?: 0) - 1
        }
        _gameState.update {
            it.copy(userInput = input, isWrongInput = isWrongInput)
        }
        Log.d(
            "DEBUG_ANKIT",
            "userInputChangeHandler: ${_gameState.value.userInput} is wrong input : ${_gameState.value.userInput} & actual word is : ${_gameState.value.currentWord}"
        )
    }

    fun buttonClick(btnType: ButtonType) {
        when (btnType) {
            ButtonType.SUBMIT_BTN -> {
                var isCorrectAns = false
                val currentScore = _gameState.value.currentScore
                if (_gameState.value.currentScrambleWord == _gameState.value.userInput) isCorrectAns = true
                _gameState.update {
                    it.copy(
                        isWrongInput = !isCorrectAns,
                        currentScore = currentScore + if (isCorrectAns) 10 else 0,
                        isSubmitted = true
                    )
                }
                Log.d(
                    "DEBUG_ANKIT",
                    "buttonClick: current score is : ${_gameState.value.currentScore}"
                )
            }

            ButtonType.NEXT_BTN -> {
                val currentQuestionNo = _gameState.value.currentQuestionNo
                if (currentQuestionNo == 10) {
                    _gameState.update { it.copy(isGameOver = true) }
                    return
                }
                updateCurrentRandomWord(getRandomWord())
            }

            ButtonType.SKIP_BTN -> {
                if (_gameState.value.isGameOver) return
                updateCurrentRandomWord(getRandomWord(), true)
            }
        }
    }

}