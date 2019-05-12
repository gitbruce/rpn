package com.bruce.calculator

import com.bruce.calculator.ArithmeticOperation.Companion.arithmeticOperationMap

class Calculator {

    private var result = 0.0
    private var reverseOperand: Double? = null

    fun execute(operator: String, firstOperand: Double, secondOperand: Double?) {
        val operation = getOperation(operator)
        result = operation.apply(firstOperand, secondOperand)
        reverseOperand = firstOperand
    }

    private fun getOperation(operator: String) : ArithmeticOperation {
        val arithmeticMap = arithmeticOperationMap()
        val operation = arithmeticMap[operator]
        if (operation == null) {
            throw CalculatorException("Operation not supported")
        } else {
            return operation
        }
    }

    fun getResult() = result

    fun getReverseOperand() = reverseOperand
}
