package com.example.newsapplication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun getImageId(categoryName: String): Int {
    return when (categoryName) {
        "all" -> R.drawable.all
        "business" -> R.drawable.business
        "entertainment" -> R.drawable.entertainment
        "general" -> R.drawable.general
        "health" -> R.drawable.health
        "science" -> R.drawable.science
        "sports" -> R.drawable.sports
        else -> R.drawable.technology
    }
}

@Composable
fun showAllCategories(shareData : (ArrayList<News>) -> Unit) {
    LazyRow(modifier = Modifier.padding(start = 15.dp, top = 10.dp)) {
        itemsIndexed(allCategories) { _, category ->

            Box(modifier = Modifier
                .padding(0.dp, 10.dp, 15.dp, 10.dp)
                .border(2.dp, Color(0F,0F,0F,0.5F))
                .width(180.dp)
                .height(100.dp)
                .clickable {
                    getData(category.urlPath, shareData = shareData)
                }
            ) {
                Image(painter = painterResource(id = getImageId(category.categoryName)),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                Color(0F, 0F, 1F, 0.3F),
                                Color(0F, 0F, 0F, 0.5F)
                            ))
                    )
                )
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = category.categoryName.capitalize(),
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                }
            }

        }
    }
}