package com.pdm0126.practica_preparcial1.TipCalculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import com.example.pdm_00097524.R
@Composable
fun TipCalculator(modifier: Modifier = Modifier){
    var amountInput by rememberSaveable { mutableStateOf("")}
    var tipPercent by rememberSaveable { mutableStateOf("")}
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val percent = tipPercent.toDoubleOrNull() ?: 10.0
    val percentError = percent !in 0.0..100.0
    var tip by rememberSaveable {mutableStateOf("") }

    Column(
        modifier = modifier.padding(horizontal = 20.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp).
                align(alignment = Alignment.Start)
        )

        TextField(
            value = amountInput,
            onValueChange = { amountInput = it },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {Text(stringResource(R.string.bill_amount))},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = tipPercent,
            onValueChange = {tipPercent = it},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {Text(stringResource(R.string.how_was_the_service))},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            isError = percentError
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button( onClick = { tip = calculateTip(amount,percent)},
            enabled = !percentError) {
            Text(text = "Calculate Tip")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

internal fun calculateTip(
    amount: Double,
    tipPercent: Double = 10.0
): String {
    val tip = tipPercent / 100 * amount
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun TipCalculatorPreview(){
    TipCalculator()
}