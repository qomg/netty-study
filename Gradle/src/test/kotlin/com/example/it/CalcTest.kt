package com.example.it

import org.junit.Test

class CalcTest {
    @Test
    fun plus() {
        var count = 0
        assert(++count >= 1)
    }
}