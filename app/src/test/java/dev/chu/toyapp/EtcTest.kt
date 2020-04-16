package dev.chu.toyapp

import org.junit.Test

class EtcTest {
    @Test
    fun testList() {
        val a = arrayListOf(1, 3, 2, "ggg")
        a.add("ㅋㅋㅋㅋ")
        a.forEach { println("$it") }
    }
}