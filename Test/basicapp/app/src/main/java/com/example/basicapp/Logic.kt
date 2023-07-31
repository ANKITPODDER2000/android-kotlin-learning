package com.example.basicapp

object Logic {

    fun fib(num: Int): Int {
        if(num <= 1) return num
        var a = 0
        var b = 1
        for(i in 0 until  num-1) {
            val c = b
            b += a
            a = c
        }
        return b
    }

}