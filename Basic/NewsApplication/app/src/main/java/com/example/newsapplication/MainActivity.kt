package com.example.newsapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapplication.ui.theme.NewsApplicationTheme

private const val API_KEY = "108272c9a3eb450f813dc4303975e772"
private const val country = "in"

private const val TAG = "MainActivity"

fun getData(urlPath: String, shareData: (ArrayList<News>) -> Unit) {
    Log.d(TAG, "GetData Called : $urlPath")
    val downloadData = DownloadData()
    downloadData.setGetDataMethod(shareData)
    downloadData.execute(urlPath)
}

class MainActivity : ComponentActivity() {

    @SuppressLint("MutableCollectionMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var allNews by remember { mutableStateOf(ArrayList<News>()) }
            val shareData = { news: ArrayList<News> -> allNews = news }
            NewsApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Log.d(TAG, "OnCreate Called ....")
                    val urlPath = "https://newsapi.org/v2/top-headlines?country=${country}&apiKey=${API_KEY}"
                    if(allNews.size == 0) getData(urlPath = urlPath, shareData)

                    if (allNews.size > 0)
                        HomePage(this, allNews, shareData)
                    else LoadingScreen()
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {

}
