package com.bruce.calculator

import java.lang.Math.pow
import kotlin.math.sqrt

enum class ArithmeticOperation (val symbol: String, val opposite: String?, val operandNumber: Int ) {
    PLUS("+", "-", 2) {
        override fun apply(a: Double, b: Double?): Double = a + b!!
    },

    MINUS("-", "+", 2) {
        override fun apply(a: Double, b: Double?): Double = b!! - a
    },

    DIVIDE("/", "*", 2) {
        override fun apply(a: Double, b: Double?): Double = b!! / a
    },

    TIMES("*", "/", 2) {
        override fun apply(a: Double, b: Double?) = a * b!!
    },

    SQRT("sqrt", "pow", 1) {
        override fun apply(a: Double, b: Double?): Double = sqrt(a)
    },

    POWER("pow", "sqrt", 1) {
        override fun apply(a: Double, b: Double?) = pow(a, 2.0)
    };

    @Throws(CalculatorException::class)
    abstract fun apply(a: Double, b: Double?): Double

    companion object {
        fun arithmeticOperationMap() : Map<String, ArithmeticOperation> {
            val operationMap = mutableMapOf<String, ArithmeticOperation>()
            for (f in ArithmeticOperation.values()) {
                operationMap[f.symbol] = f
            }
            return operationMap
        }
    }
}
