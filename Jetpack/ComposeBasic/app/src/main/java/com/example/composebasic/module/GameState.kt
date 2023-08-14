package com.example.composebasic.module

data class GameState(
    var currentWord: String = "",
    var currentScore: Int = 0,
    var currentQuestionNo: Int = 0,
    var usedWords: HashSet<String> = hashSetOf(),
    var currentScrambleWord: String = "",
    val isGameOver: Boolean = false,
    var userInput: String = "",
    var isWrongInput: Boolean = false,
    var isSubmitted: Boolean = false
)