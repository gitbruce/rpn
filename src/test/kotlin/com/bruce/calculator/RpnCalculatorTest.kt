package com.bruce.calculator

import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.Test as test


class `Test RPN Calculator` {


    @test
    fun `Test example 1`() {
        val rpn = RpnInvoker()
        rpn.parse("5 2")
        assertEquals(rpn.valueStack.peek(), 2.0)
    }

    @test
    fun `Test example 2`() {
        val rpn = RpnInvoker()
        rpn.parse("2 sqrt")
        assertEquals(rpn.valueStack.peek(), 1.4142135623730951)

        rpn.parse("clear 9 sqrt")
        assertEquals(rpn.valueStack.peek(), 3.0)
    }

    @test
    fun `Test example 3`() {
        val rpn = RpnInvoker()
        rpn.parse("5 2 -")
        assertEquals(rpn.valueStack.peek(), 3.0)

        rpn.parse("3 -")
        assertEquals(rpn.valueStack.peek(), 0.0)

        rpn.parse("clear")
        assertEquals(rpn.valueStack.size, 0)
    }

    @test
    fun `Test example 4`() {
        val rpn = RpnInvoker()
        rpn.parse("5 4 3 2")
        assertEquals(rpn.valueStack.size, 4)

        rpn.parse("undo undo *")
        assertEquals(rpn.valueStack.peek(), 20.0)

        rpn.parse("5 *")
        assertEquals(rpn.valueStack.peek(), 100.0)

        rpn.parse("undo")
        assertEquals(rpn.valueStack.peek(), 5.0)
        assertEquals(rpn.valueStack.size, 2)
    }

    @test
    fun `Test example 5`() {
        val rpn = RpnInvoker()
        rpn.parse("7 12 2 /")
        assertEquals(rpn.valueStack.size, 2)
        assertEquals(rpn.valueStack.peek(), 6.0)

        rpn.parse("*")
        assertEquals(rpn.valueStack.peek(), 42.0)

        rpn.parse("4 /")
        assertEquals(rpn.valueStack.peek(), 10.5)
    }

    @test
    fun `Test example 6`() {
        val rpn = RpnInvoker()
        rpn.parse("1 2 3 4 5")
        assertEquals(rpn.valueStack.size, 5)
        assertEquals(rpn.valueStack.peek(), 5.0)

        rpn.parse("*")
        assertEquals(rpn.valueStack.size, 4)
        assertEquals(rpn.valueStack.peek(), 20.0)

        rpn.parse("clear 3 4 -")
        assertEquals(rpn.valueStack.peek(), -1.0)
    }

    @test
    fun `Test example 7`() {
        val rpn = RpnInvoker()
        rpn.parse("1 2 3 4 5")
        rpn.parse("* * * *")
        assertEquals(rpn.valueStack.peek(), 120.0)
    }

    @test
    fun `Test example 8`() {
        val rpn = RpnInvoker()
        assertFailsWith<CalculatorException>{ rpn.parse("1 2 3 * 5 + * * 6 5") }
        assertEquals(rpn.valueStack.peek(), 11.0)
    }
}

