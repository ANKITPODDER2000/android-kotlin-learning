package com.example.todoapp.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.viewmodel.TopAppBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoAppScreen(topAppBarViewModel: TopAppBarViewModel) {
    Scaffold(
        topBar = { TodoTopBar(topAppBarViewModel,"Task") },
        floatingActionButton = { TodoFloatingActionButton() }
    ) {
        TodoNavHost()
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTodoAppScreen() {
    TodoAppScreen(TopAppBarViewModel())
}