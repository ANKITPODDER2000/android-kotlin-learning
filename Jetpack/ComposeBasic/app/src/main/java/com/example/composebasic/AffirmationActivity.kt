package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.module.Affirmation
import com.example.composebasic.module.AffirmationList

class AffirmationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationContentList(AffirmationList.contentList)
        }
    }
}

@Composable
fun AffirmationContentList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(affirmationList) {
            AffirmationCard(it)
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageId),
                contentDescription = stringResource(id = affirmation.titleId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
                    .padding(16.dp),
            )
            Text(
                text = stringResource(id = affirmation.titleId),
                modifier = Modifier.padding(16.dp, 0.dp, 16.dp, 16.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewAffirmationContentList() {
    AffirmationContentList(AffirmationList.contentList)
}

@Preview(showBackground = true, widthDp = 320, heightDp = 700)
@Composable
fun PreviewAffirmationCard() {
    AffirmationCard(AffirmationList.contentList[0])
}
