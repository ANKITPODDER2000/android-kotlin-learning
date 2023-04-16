package com.example.checklifecyclewithstate

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.checklifecyclewithstate.ui.theme.CheckLifeCycleWithStateTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Oncreate 1")
        setContent {
            var a by remember { mutableStateOf(false) }
            CheckLifeCycleWithStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Log.d(TAG, "Oncreate 2")
                    Greeting("Android", a) {
                        Unit ->
                        a = !a
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, a : Boolean, func : (Unit) -> Unit) {
    Log.d(TAG, "Composable Function called ....")
    // var a by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .clickable { func(Unit) }
        .fillMaxSize()
        .background(MaterialTheme.colors.secondaryVariant)) {
        Text(text = "Value of a : ${a.toString()}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}