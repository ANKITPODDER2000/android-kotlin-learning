package com.example.coroutinetest

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.pow

class MainHelper @Inject constructor(){
    fun generateRandomNumber(listener: Listener, dispatcher: CoroutineDispatcher) {
        Log.d("DEBUG_ANKIT", "generateRandomNumber: is called")
        CoroutineScope(dispatcher).launch {
            for(i in 0 ..10.0.pow(10.0).toLong()) {

            }
            val randomNumber = 100
            listener.getRandomNumber(randomNumber)
        }
    }
}