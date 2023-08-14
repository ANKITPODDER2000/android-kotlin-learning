package com.example.composebasic.viewmodel

import com.example.composebasic.module.GameState
import com.example.composebasic.util.ButtonType
import kotlinx.coroutines.flow.StateFlow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UnscrambleViewModelTest {

    private lateinit var viewModel: UnscrambleViewModel
    private lateinit var gameState: StateFlow<GameState>
    @Before
    fun setUp() {
        viewModel = UnscrambleViewModel()
        gameState = viewModel.gameState
    }

    @Test
    fun `check initNewGame after few operations` () {
        for(i in 0 .. 7) {
            viewModel.buttonClick(ButtonType.SUBMIT_BTN)
            viewModel.buttonClick(ButtonType.NEXT_BTN)
            viewModel.updateCurrentRandomWord("Hello")
        }
        viewModel.initNewGame()

        assertEquals(gameState.value.currentScore, 0)
        assertEquals(gameState.value.currentQuestionNo, 1)
        assertEquals(gameState.value.userInput, "")
        assertEquals(gameState.value.isGameOver, false)
        assertEquals(gameState.value.usedWords.size, 1)
        assertEquals(gameState.value.isSubmitted, false)
        assertEquals(gameState.value.isWrongInput, false)
        assertNotEquals(gameState.value.currentWord, "")
        assertNotEquals(gameState.value.currentWord, gameState.value.currentScrambleWord)

    }

    @Test
    fun `check getRandomWord` () {
        for(i in 0 .. 25) {
            val getRandomWordMethod = viewModel.javaClass.getDeclaredMethod("getRandomWord")
            getRandomWordMethod.isAccessible = true
            val word = getRandomWordMethod.invoke(viewModel) as String

            assertFalse(viewModel.gameState.value.usedWords.contains(word))
            viewModel.updateCurrentRandomWord(word)
        }
    }

    @Test
    fun `check getShuffledWord` () {
        val method = viewModel.javaClass.getDeclaredMethod("getShuffledWord", String::class.java)
        method.isAccessible = true
        for(i in 0 until  1000) {
            val word = method.invoke(viewModel, "ank")
            assertNotEquals(word, "ank")
        }
    }

    @Test
    fun `check userInputChangeHandler with wrong input` () {
        viewModel.updateCurrentRandomWord("ankit")
        viewModel.userInputChangeHandler("raja")

        assertEquals(viewModel.gameState.value.userInput, "raja")
        assertTrue(viewModel.gameState.value.isWrongInput)
    }

    @Test
    fun `check userInputChangeHandler with correct input` () {
        viewModel.updateCurrentRandomWord("ankit")
        viewModel.userInputChangeHandler("ank")

        assertEquals(viewModel.gameState.value.userInput, "ank")
        assertFalse(viewModel.gameState.value.isWrongInput)
    }

    @Test
    fun `check submit btn click for wrong input`() {
        viewModel.buttonClick(ButtonType.SUBMIT_BTN)
        viewModel.gameState.value.apply {
            assertTrue(isWrongInput)
            assertTrue(isSubmitted)
            assertEquals(currentScore, 0)
        }
    }

    @Test
    fun `check submit btn click for right input`() {
        viewModel.userInputChangeHandler(viewModel.gameState.value.currentScrambleWord)
        viewModel.buttonClick(ButtonType.SUBMIT_BTN)
        viewModel.gameState.value.apply {
            assertFalse(isWrongInput)
            assertTrue(isSubmitted)
            assertEquals(currentScore, 10)
        }
    }

    @Test
    fun `skip the 1st question`() {
        val word = viewModel.gameState.value.currentWord
        val score = viewModel.gameState.value.currentScore
        viewModel.buttonClick(ButtonType.SKIP_BTN)
        assertNotEquals(word, viewModel.gameState.value.currentWord)
        assertEquals(score ,viewModel.gameState.value.currentScore)
    }

    @Test
    fun `check next button after game end`() {
        viewModel.gameState.value.currentQuestionNo = 10
        val word = viewModel.gameState.value.currentWord
        viewModel.buttonClick(ButtonType.NEXT_BTN)
        assertEquals(word, viewModel.gameState.value.currentWord)
        assertTrue(viewModel.gameState.value.isGameOver)
    }

    @Test
    fun `check next button while playing`() {
        viewModel.gameState.value.currentQuestionNo = 6
        val word = viewModel.gameState.value.currentWord
        viewModel.buttonClick(ButtonType.NEXT_BTN)
        assertNotEquals(word, viewModel.gameState.value.currentWord)
        assertFalse(viewModel.gameState.value.isGameOver)
        assertEquals(viewModel.gameState.value.currentQuestionNo, 6+1)
        assertFalse(viewModel.gameState.value.isWrongInput)
    }
}