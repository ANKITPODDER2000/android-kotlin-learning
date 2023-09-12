package com.example.composebasic.navscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.R
import com.example.composebasic.util.CupCakeDataSource.quantityOptions

@Composable
fun CupCakeHomeScreen(modifier: Modifier = Modifier, navigateToOptionPage: () -> Unit = {}) {
    Column(
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_16_dp),
                vertical = dimensionResource(id = R.dimen.padding_32_dp)
            )
            .verticalScroll(
                rememberScrollState()
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.cupcakeimage),
            contentDescription = "Cup Cake Image",
            modifier = modifier
                .fillMaxWidth(1f)
                .padding(bottom = dimensionResource(id = R.dimen.padding_24_dp))
        )
        Text(
            text = "Order Cupcakes",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_32_dp))
        )
        OrderButtons(navigateToOptionPage)
    }
}

@Composable
fun OrderButtons(navigateToOptionPage: () -> Unit) {
    Column {

        quantityOptions.map {
            Pair(stringResource(id = it.first), it.second)
        }.forEach {
            Button(
                onClick = { navigateToOptionPage() },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = dimensionResource(id = R.dimen.padding_8_dp)),
                shape = RectangleShape
            ) {
                Text(text = it.first)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCupCakeHomeScreen() {
    CupCakeHomeScreen()
}