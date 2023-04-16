package com.example.memeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.example.memeapp.ui.theme.MemeAppTheme
import androidx.compose.ui.*
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter


private const val urlPath = "https://meme-api.com/gimme"

data class Meme(var title: String, var url: String)

private lateinit var downloadData: DownloadData

fun getMeme(updateMeme: (Meme) -> Unit) {

    Log.d("MainActivity", "Called..... | Thread Name : ${Thread.currentThread().name}")

    downloadData = DownloadData()
    downloadData.setMemeInstance(updateMeme)
    downloadData.execute(urlPath)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Log.d("MainActivity", "onCreateMethod Called..... | Thread Name : ${Thread.currentThread().name}")
            var meme: Meme by remember {
                mutableStateOf(Meme("Random Joke",
                    "https://i.redd.it/ta8de39pa6sa1.jpg"))
            }
            val updateMeme: (Meme) -> Unit = { newMeme: Meme -> meme = newMeme }
            MemeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    getMeme(updateMeme)
                    ShowMeme(this, meme, updateMeme)
                }
            }
        }
    }
}


@Composable
fun ShowMeme(mainActivity: MainActivity,meme: Meme, updateMeme: (Meme) -> Unit) {
    // Text(text = name.toString())
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxHeight(0.8F)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(meme.url),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
        Row(
            modifier = Modifier
                .fillMaxHeight(0.8F)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "Checkout this meme ${meme.url}")
                val chooser = Intent.createChooser(intent, "Share this meme using...")
                mainActivity.startActivity(chooser)
            }) {
                Text(text = "Share")
            }
            Button(onClick = {
                getMeme(updateMeme)
            }) {
                Text(text = "Next")
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MemeAppTheme {
        ShowMeme(Meme("Dummy Image", "https://i.redd.it/ta8de39pa6sa1.jpg"))
    }
}
*/