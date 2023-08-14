package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.util.ArtSpaceUtil

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentArtSpacePos by remember { mutableStateOf(0) }
            ArtSpaceGallery(currentArtSpacePos, Modifier) { currentArtSpacePos = it }
        }
    }
}

@Composable
fun ArtSpaceGallery(
    artSpaceCurrentPos: Int,
    modifier: Modifier = Modifier,
    updateCurrentArtSpacePos: (Int) -> Unit,
) {
    val artSpace = ArtSpaceUtil.allArtSpaces[artSpaceCurrentPos]
    Column(
        modifier = modifier
            .fillMaxSize(1f)
            .padding(40.dp, 16.dp, 40.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImageGallery(artSpace.imgResId, artSpace.titleResId)
        Spacer(modifier = Modifier.height(32.dp))
        ImageDetails(artSpace.titleResId, artSpace.authorResId)
        ButtonLayout(artSpaceCurrentPos, updateCurrentArtSpacePos)
    }
}

@Composable
fun ButtonLayout(artSpaceCurrentPos: Int, updateCurrentArtSpacePos: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(modifier = Modifier.fillMaxWidth(1f)) {
            Button(
                onClick = { updateCurrentArtSpacePos((artSpaceCurrentPos - 1) % 2) },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = stringResource(R.string.previous))
            }
            Button(
                onClick = { updateCurrentArtSpacePos((artSpaceCurrentPos + 1) % 2) },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}

@Composable
fun ImageDetails(
    @StringRes imageTitle: Int,
    @StringRes authorId: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.background(Color.Cyan)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(16.dp, 24.dp)
        ) {
            Text(
                text = stringResource(id = imageTitle),
                fontSize = 22.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = stringResource(id = authorId),
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
fun ImageGallery(
    @DrawableRes imageId: Int,
    @StringRes stringId: Int,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.fillMaxWidth(1f).fillMaxHeight(0.6f), shadowElevation = 24.dp) {
        Image(
            modifier = Modifier.padding(16.dp),
            painter = painterResource(id = imageId),
            contentDescription = stringResource(id = stringId),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArtSpaceGallery() {
    ArtSpaceGallery(0, Modifier) {}
}