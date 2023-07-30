package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class BirthdayWish : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayGreeting(
                birthdayBoyOrGirlName = "Ankita",
                greetedBy = "Ankit",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun BirthdayGreeting(
    birthdayBoyOrGirlName: String,
    greetedBy: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Text(
            text = "Happy Birthday $birthdayBoyOrGirlName",
            fontSize = 100.sp,
            lineHeight = 108.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "From $greetedBy",
            fontSize = 36.sp,
            lineHeight = 42.sp,
            modifier = Modifier.align(alignment = Alignment.End).padding(16.dp, 32.dp)
        )
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewBirthdayGreeting() {
    BirthdayGreeting(birthdayBoyOrGirlName = "Ankita", greetedBy = "Ankit")
}