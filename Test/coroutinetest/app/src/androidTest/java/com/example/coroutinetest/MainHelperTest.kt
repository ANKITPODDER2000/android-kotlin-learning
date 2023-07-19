package com.example.coroutinetest

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainHelperTest {

    private lateinit var mainHelper: MainHelper
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        mainHelper = MainHelper()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun generateRandomNumber1() = runTest(testDispatcher){
        mainHelper.generateRandomNumber(object : Listener {
            override fun getRandomNumber(num: Int) {
                println( "getRandomNumber: got the result, number is : $num")
                assertThat(100 == num).isTrue()
            }
        }, testDispatcher)

        advanceUntilIdle()

        println("generateRandomNumber: is about to end")
    }

    @Test
    fun generateRandomNumber2() = runTest(testDispatcher){
        mainHelper.generateRandomNumber(object : Listener {
            override fun getRandomNumber(num: Int) {
                println( "getRandomNumber: got the result, number is : $num")
                assertThat(102 == num).isFalse()
            }
        }, testDispatcher)

        advanceUntilIdle()

        println("generateRandomNumber: is about to end")
    }


    @Test
    fun generateRandomNumber3() = runTest(testDispatcher){
        mainHelper.generateRandomNumber(object : Listener {
            override fun getRandomNumber(num: Int) {
                println( "getRandomNumber: got the result, number is : $num")
                assertThat(102 == num).isTrue()
            }
        }, testDispatcher)

        advanceUntilIdle()

        println("generateRandomNumber: is about to end")
    }
}


// Android gradle plugin version : 7.2.1
// gradle version : 7.4.2
// Plugins
/*
plugins {
    id 'com.android.application' version '7.2.1' apply false
    id 'com.android.library' version '7.2.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.8.20' apply false
}*/
