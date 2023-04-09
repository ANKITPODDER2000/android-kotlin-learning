package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.animation.*
import androidx.compose.material.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // MessageBox(message = SampleData.conversationSample[1])
            LazyColumn(modifier = Modifier.padding(0.dp, 15.dp)) {
                itemsIndexed(SampleData.conversationSample) { _, msg ->
                    MessageBox(message = msg)
                }
            }
        }
    }
}

data class Message(val user: String, val text: String)

@Composable
fun MessageBox(message: Message) {
    var isExpanded by remember { mutableStateOf(false) }
    val surfaceColor by animateColorAsState(targetValue = if (isExpanded) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.surface)
    Row(
        modifier = Modifier
            .padding(20.dp, 15.dp)
            .fillMaxWidth()
            .clickable {
                isExpanded = !isExpanded
            },
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "User Image",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
                .size(60.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = message.user,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2)
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(5.dp, 10.dp)
            ) {
                Text(
                    text = message.text,
                    style = MaterialTheme.typography.body2,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(10.dp, 5.dp).fillMaxWidth()
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        MessageBox(message = Message("Ankit" , "Hi its Ankit this side..."))
//    }
//}