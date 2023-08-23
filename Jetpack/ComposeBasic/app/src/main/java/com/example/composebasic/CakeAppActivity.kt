package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.composebasic.navscreens.CakeAppScreen
import com.example.composebasic.viewmodel.CupCakeViewModel

class CakeAppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CupCakeViewModel by viewModels()
        setContent {
            CakeAppScreen(viewModel)
        }
    }
}