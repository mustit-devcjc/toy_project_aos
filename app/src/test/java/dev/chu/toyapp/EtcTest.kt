package dev.chu.toyapp

import org.junit.Assert
import org.junit.Test

class EtcTest {
    @Test
    fun testList() {
//        println(calculator(1, 3) { a, b -> a + b})
        // same code
//        val func: Function2<Int, Int, Int> = {a, b -> a+b}
//        println(calculator(3, 4, func))
//
//
        println(calculator(1, 3) { a, b -> a + b})
        println(calculator(1, 3) { a, b -> a - b})
        println(calculator(1, 3) { a, b -> a * b})
        println(calculator(1, 3) { a, b -> a / b})
    }

//    @Test
//    fun printResultSumTest() {
//        Assert.assertEquals(15, printResult(::sum))
//    }
//
//    @Test
//    fun printResultSubtractTest(){
//        Assert.assertEquals(5,printResult(::subtract))
//    }

    fun calculator(a: Int, b: Int, body: (Int, Int) -> Int): Int {
        return body(a, b)
    }
}