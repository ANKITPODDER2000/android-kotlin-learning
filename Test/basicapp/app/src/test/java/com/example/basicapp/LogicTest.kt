package com.example.basicapp


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LogicTest {
    @Test
    fun `check fib for 0` () {
        val fib0 = Logic.fib(0)
        assertThat(fib0 == 0).isTrue()
    }

    @Test
    fun `check fib for 1` () {
        val fib1 = Logic.fib(1)
        assertThat(fib1 == 1).isTrue()
    }

    @Test
    fun `check fib for 10` () {
        val fib10 = Logic.fib(10)
        assertThat(fib10 == 55).isTrue()
    }

    @Test
    fun `check fib for 25` () {
        val fib25 = Logic.fib(25)
        assertThat(fib25 == 75025).isTrue()
    }
}