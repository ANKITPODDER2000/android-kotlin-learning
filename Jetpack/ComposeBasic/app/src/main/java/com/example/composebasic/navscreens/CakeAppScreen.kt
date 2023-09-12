package com.example.composebasic.navscreens

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composebasic.viewmodel.CupCakeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CakeAppScreen(viewModel: CupCakeViewModel, navHostController: NavHostController = rememberNavController()) {
    val activity = LocalContext.current as Activity
    Scaffold(
        topBar = {
            CakeTopBar(title = "Home Screen", modifier = Modifier) {
                if (navHostController.previousBackStackEntry == null)
                    activity.finish()
                else navHostController.navigateUp()
            }
        }) { innerPadding ->
        CakeNavHost(viewModel, navHostController, modifier = Modifier.padding(innerPadding))
    }
}


@Preview
@Composable
fun PreviewCakeAppScreen() {
    CakeAppScreen(CupCakeViewModel())
}