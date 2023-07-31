package com.example.composebasic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(this@MainActivity, "Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(context: Context?, name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(1f)
            .background(color = Color.DarkGray)
            .padding(16.dp, 32.dp)
    ) {
        Text(
            color = Color.White,
            modifier = Modifier,
            text = "Hello $name!",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = {
                context?.let {
                    Intent(context, BirthdayWish::class.java).also {
                        context.startActivity(it)
                    }
                }
            },
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                .fillMaxWidth(1f)
        ) {
            Text(text = "Birthday Greeting")
        }
        Button(
            onClick = {
                context?.let {
                    Intent(context, DiceRollActivity::class.java).also {
                        context.startActivity(it)
                    }
                }
            },
            modifier = Modifier
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
                .fillMaxWidth(1f)
        ) {
            Text(text = "Dice Roll")
        }
    }
}
