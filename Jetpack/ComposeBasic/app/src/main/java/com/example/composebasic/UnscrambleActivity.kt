package com.example.composebasic

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.util.ButtonType
import com.example.composebasic.util.ButtonType.NEXT_BTN
import com.example.composebasic.util.ButtonType.SKIP_BTN
import com.example.composebasic.util.ButtonType.SUBMIT_BTN
import com.example.composebasic.viewmodel.UnscrambleViewModel

class UnscrambleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: UnscrambleViewModel by viewModels()
        setContent {
            val gameState by viewModel.gameState.collectAsState()
            if (gameState.isGameOver)
                GameOverDialog(finalScore = gameState.currentScore) {
                    viewModel.initNewGame()
                }
            else GameLayout(viewModel)
        }
    }
}

@Composable
fun GameLayout(viewModel: UnscrambleViewModel, modifier: Modifier = Modifier) {
    val gameState by viewModel.gameState.collectAsState()
    Log.d("DEBUG_ANKIT", "GameLayout: name : ${gameState.currentScrambleWord}")
    Column(
        modifier = modifier
            .fillMaxSize(1f)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameTitle()
        GameField(
            gameState.currentScrambleWord,
            gameState.userInput,
            gameState.currentQuestionNo,
            gameState.isWrongInput,
            Modifier
        ) {
            viewModel.userInputChangeHandler(it)
        }
        GameButtons(gameState.isSubmitted) { btnType ->
            viewModel.buttonClick(btnType)
        }
        GameScore(gameState.currentScore)
    }
}

@Composable
fun GameScore(gameScore: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Text(
            text = stringResource(R.string.score, gameScore),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun GameButtons(isSubmitted: Boolean, buttonClick: (ButtonType) -> Unit) {
    Button(
        onClick = { if (isSubmitted) buttonClick(NEXT_BTN) else buttonClick(SUBMIT_BTN) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = if (isSubmitted) stringResource(id = R.string.next) else stringResource(R.string.submit),
            fontSize = 16.sp
        )
    }
    if (!isSubmitted)
        OutlinedButton(
        onClick = { buttonClick(SKIP_BTN) }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(text = stringResource(R.string.skip), fontSize = 16.sp)
    }
}

@Composable
fun GameField(
    scrambleWord: String,
    userInput: String,
    currentQuestionNo: Int,
    isWrongInput: Boolean,
    modifier: Modifier = Modifier,
    userInputChangeHandler: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(1f)
            .padding(top = 16.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)) {
            QuestionNumberField(currentQuestionNo)
            ScrambleWord(scrambleWord)
            UserGuessBox(userInput, userInputChangeHandler, isWrongInput)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun UserGuessBox(
    userInput: String,
    userInputChangeHandler: (String) -> Unit,
    isWrongInput: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        Text(
            text = stringResource(R.string.unscramble_the_word_using_all_letters),
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        OutlinedTextField(
            value = userInput,
            isError = isWrongInput,
            onValueChange = { userInputChangeHandler(it) },
            modifier = Modifier.fillMaxWidth(1f),
            colors = TextFieldDefaults.textFieldColors(containerColor = colorScheme.surface),
            label = { Text(text = stringResource(R.string.enter_your_word)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            })
        )
    }
}

@Composable
fun ScrambleWord(scrambleWord: String, modifier: Modifier = Modifier) {
    Text(
        text = scrambleWord,
        fontSize = 32.sp,
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(1f),
        textAlign = TextAlign.Center
    )
}

@Composable
fun QuestionNumberField(questionNo: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.game_score, questionNo),
            modifier = Modifier
                .align(Alignment.End)
                .clip(shapes.medium)
                .background(colorScheme.surfaceTint)
                .padding(16.dp, 8.dp),
            color = colorScheme.onPrimary,
            style = typography.titleMedium
        )
    }
}

@Composable
fun GameTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.unscramble),
        modifier = modifier,
        style = typography.titleLarge
    )
}

@Composable
fun GameOverDialog(finalScore: Int, resetGame: () -> Unit) {
    val activity = LocalContext.current as Activity
    AlertDialog(onDismissRequest = { }, confirmButton = {
        TextButton(onClick = { resetGame() }) {
            Text(text = stringResource(R.string.play_again))
        }
    }, modifier = Modifier, dismissButton = {
        TextButton(onClick = { activity.finish() }) {
            Text(text = stringResource(R.string.exit))
        }
    }, title = { Text(text = stringResource(id = R.string.congratulations)) }, text = {
        Text(
            text = stringResource(
                R.string.final_score_msg, finalScore
            )
        )
    })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewGameLayout() {
    GameLayout(UnscrambleViewModel())
}