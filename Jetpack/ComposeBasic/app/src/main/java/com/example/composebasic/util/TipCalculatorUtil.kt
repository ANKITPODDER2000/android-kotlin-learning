package com.example.composebasic.util

import java.text.NumberFormat

class TipCalculatorUtil {
    companion object {
        private const val TIP_PCT = 10.0
        fun calculateTip(amount : Double) : String {
            val tipAmount = (amount / 100) * TIP_PCT
            return NumberFormat.getCurrencyInstance().format(tipAmount)
        }
    }
}