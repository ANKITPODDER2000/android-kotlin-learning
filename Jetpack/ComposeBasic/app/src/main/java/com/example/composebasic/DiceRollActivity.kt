package com.example.composebasic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.util.DiceUtil.Companion.getDiceImage
import com.example.composebasic.util.DiceUtil.Companion.getRandomDiceNo


class DiceRollActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRoll()
        }
    }
}

@Composable
fun DiceRoll(modifier: Modifier = Modifier) {
    var currentDice by remember { mutableStateOf(getRandomDiceNo()) }
    Log.d("DEBUG_ANKIT", "DiceRoll: is called")
    Column(modifier = modifier.fillMaxSize(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = getDiceImage(currentDice)),
            contentDescription = stringResource(R.string.currently_showing_dice_no, currentDice)
        )
        Button(onClick = {
            currentDice = getRandomDiceNo()
        }, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text(text = stringResource(R.string.roll_dice))
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewDiceRoll() {
    DiceRoll()
}