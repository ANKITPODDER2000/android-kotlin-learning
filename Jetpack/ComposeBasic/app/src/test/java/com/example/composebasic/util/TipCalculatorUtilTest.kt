package com.example.composebasic.util

import org.junit.Assert.*
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorUtilTest {
    @Test
    fun `check tip for amount 100` () {
        assertEquals(NumberFormat.getCurrencyInstance().format(10.00), TipCalculatorUtil.calculateTip(100.toDouble()))
    }
    @Test
    fun `check tip for amount 355-555` () {
        assertEquals(NumberFormat.getCurrencyInstance().format(35.5444), TipCalculatorUtil.calculateTip(355.444))
    }
}