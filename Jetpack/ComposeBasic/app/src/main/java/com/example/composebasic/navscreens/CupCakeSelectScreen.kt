package com.example.composebasic.navscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.R
import com.example.composebasic.util.CupCakeDataSource
import com.example.composebasic.viewmodel.CupCakeViewModel

@Composable
fun CupCakeSelectScreen(
    selectionList: List<String>,
    onCancelButtonClickListener: () -> Unit,
    onNextButtonClickListener: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedOption by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.padding(
            horizontal = dimensionResource(id = R.dimen.padding_16_dp),
            vertical = dimensionResource(id = R.dimen.padding_32_dp)
        )
    ) {
        selectionList.forEach {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth(1f)
                    .selectable(
                        selected = selectedOption == it,
                        onClick = { selectedOption = it }
                    ), verticalAlignment = CenterVertically) {
                    RadioButton(selected = (selectedOption == it),
                        onClick = { selectedOption = it })
                    Text(text = it)
                }
            }
        }
        Divider(thickness = 2.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(top = dimensionResource(id = R.dimen.padding_32_dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { onCancelButtonClickListener() },
                shape = RectangleShape,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = dimensionResource(id = R.dimen.padding_8_dp))
            ) {
                Text(text = stringResource(R.string.cancel))
            }
            Button(
                onClick = { onNextButtonClickListener() },
                shape = RectangleShape,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dimensionResource(id = R.dimen.padding_8_dp))
            ) {
                Text(text = stringResource(R.string.next))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCupCakeSelectScreen1() {
    CupCakeSelectScreen(CupCakeDataSource.flavors.map { stringResource(id = it) }, { }, { })
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCupCakeSelectScreen2() {
    CupCakeSelectScreen(CupCakeViewModel().pickupOptions(), { }, { })
}