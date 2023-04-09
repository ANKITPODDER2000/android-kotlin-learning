package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var userName by remember { mutableStateOf("") }

            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    GetUserName(this, userName) { it: String -> userName = it }
                }
            }
        }
    }
}

@Composable
fun GetUserName(
    mainActivityContext: MainActivity,
    userName: String,
    updateUserName: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text("Birthday Greeting App", fontSize = 30.sp, modifier = Modifier.padding(bottom = 20.dp))
        TextField(value = userName,
            onValueChange = { it ->
                updateUserName(it)
            },
            modifier = Modifier
                .padding(bottom = 20.dp)
                .border(2.dp, MaterialTheme.colors.secondaryVariant)
        )

        Button(onClick = {
            val intent = Intent(mainActivityContext, Greetuser::class.java)
            intent.putExtra("userName", userName)
            mainActivityContext.startActivity(intent)
        },
            modifier = Modifier.background(MaterialTheme.colors.secondaryVariant)) {
            Text(text = "Greet Your Friend....", fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        // GetUserName("Test User") {}
    }
}