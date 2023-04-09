package com.example.newsapplication

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.newsapplication.ui.theme.NewsApplicationTheme
import androidx.browser.customtabs.CustomTabsIntent


class NewsDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val news = intent.getSerializableExtra("news") as News
                    NewsDetails(this, news = news)
                }
            }
        }
    }
}

@Composable
fun NewsDetails(newsDetailActivity: NewsDetailActivity, news: News) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(15.dp, 30.dp)
        .verticalScroll(rememberScrollState())
    ) {
        Text(text = news.title,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp)
        Spacer(modifier = Modifier.height(15.dp))
        Image(painter =
        if (news.urlToImage != "null") rememberImagePainter(data = news.urlToImage)
        else painterResource(id = R.drawable.noimg),
            contentDescription = news.description,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(15.dp))
        if (news.author != "null") {
            Text(text = "Author : ${news.author}")
            Spacer(modifier = Modifier.height(15.dp))
        }
        if (news.publishedAt != "null") {
            Text(text = "Published : ${
                news.publishedAt.substring(0,
                    news.publishedAt.indexOf('T'))
            }")
            Spacer(modifier = Modifier.height(15.dp))
        }
        Text(text = news.description)
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = news.content)
        Spacer(modifier = Modifier.height(15.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(MaterialTheme.colors.secondaryVariant)
            .shadow(5.dp),
            shape = RoundedCornerShape(5.dp),
            onClick = {
                val url = news.url
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(newsDetailActivity, Uri.parse(url))
            }
        ) {
            Text(text = "Read full Article")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {

}