package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.util.TipCalculatorUtil

class TipCalculatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculateLayout()
        }
    }
}

@Composable
fun TipCalculateLayout(modifier: Modifier = Modifier) {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tipAmount = TipCalculatorUtil.calculateTip(amount)
    Column(
        modifier = modifier
            .fillMaxSize(1f)
            .padding(24.dp, 48.dp)
    ) {
        Text(text = stringResource(R.string.calculatetip), style = MaterialTheme.typography.titleSmall)
        TipInputField(amountInput) {
            amountInput = it
        }
        Text(
            text = stringResource(R.string.total_tip, tipAmount),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.fillMaxWidth(1f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipInputField(amountInput: String, onAmountChange: (String) -> Unit) {
    TextField(
        value = amountInput,
        onValueChange = onAmountChange,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(0.dp, 16.dp, 0.dp, 32.dp),
        label = { Text(text = stringResource(R.string.bill_amount)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTipCalculateLayout() {
    TipCalculateLayout()
}