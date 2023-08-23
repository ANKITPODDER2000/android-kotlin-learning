package com.example.composebasic.navscreens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composebasic.util.CupCakeDataSource
import com.example.composebasic.viewmodel.CupCakeViewModel

@Composable
fun CakeNavHost(
    viewModel: CupCakeViewModel,
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    NavHost(
        navController = navHostController,
        startDestination = NavScreens.START.name,
        modifier = modifier
    ) {
        composable(NavScreens.START.name) {
            CupCakeHomeScreen() {
                navHostController.navigate(NavScreens.FLAVOR.name)
            }
        }
        composable(NavScreens.FLAVOR.name) {
            CupCakeSelectScreen(
                selectionList = CupCakeDataSource.flavors.map { context.getString(it) },
                onCancelButtonClickListener = {},
                onNextButtonClickListener = {
                    navHostController.navigate(NavScreens.PICKUP.name)
                })
        }
        composable(NavScreens.PICKUP.name) {
            CupCakeSelectScreen(
                selectionList = viewModel.pickupOptions(),
                onCancelButtonClickListener = {},
                onNextButtonClickListener = {
                    navHostController.navigate(NavScreens.PICKUP.name)
                })
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewCakeNavHost() {
    CakeNavHost(CupCakeViewModel(), navHostController = rememberNavController())
}