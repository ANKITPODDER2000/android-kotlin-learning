package com.example.newsapplication

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter


@Composable
fun HomePage(mainActivity: MainActivity, allNews: ArrayList<News>, shareData : (ArrayList<News>) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(0.dp, 10.dp, 0.dp, 30.dp)) {
        showAllCategories(shareData)
        ShowAllNews(mainActivity = mainActivity, allNews = allNews)
    }
}

@Composable
fun ShowAllNews(mainActivity: MainActivity, allNews: ArrayList<News>) {
    LazyColumn {
        itemsIndexed(allNews) { _, news ->
            NewsCard(mainActivity = mainActivity, news)
        }
    }
}


@Composable
fun NewsCard(mainActivity: MainActivity, news: News, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(15.dp, 20.dp)
        .clickable {
            val intent = Intent(mainActivity, NewsDetailActivity::class.java)
            intent.putExtra("news", news)
            mainActivity.startActivity(intent)
        }) {
        Image(painter =
        if (news.urlToImage != "null") rememberImagePainter(data = news.urlToImage)
        else painterResource(id = R.drawable.noimg),
            contentDescription = news.description,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.height(10.dp))
        Text(news.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}
