package com.example.coroutinetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Listener {
    @Inject lateinit var mainHelper: MainHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainHelper.generateRandomNumber(this, Dispatchers.IO)
        Log.d("DEBUG_ANKIT", "onCreate: is about to end")
    }

    override fun getRandomNumber(num: Int) {
        Log.d("DEBUG_ANKIT", "getRandomNumber: $num")
    }
}