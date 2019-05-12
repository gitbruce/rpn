package com.bruce.calculator

import java.text.DecimalFormat
import java.util.*

class RpnInvoker {

    val valueStack = Stack<Double>()
    val undoStack = Stack<Command>()
    private val calculator = Calculator()
    private var positionIndex = 0

    fun parse(input: String) {
        positionIndex = 1
        val tokens = input.trim().split(" ")
        for (token in tokens) {
            if (StringUtils.isNumeric(token)) {
                valueStack.push(token.toDouble())
                undoStack.push(null)
            } else {
                when (token.toLowerCase()) {
                    "clear" -> {
                        valueStack.clear()
                        undoStack.clear()
                    }
                    "undo" -> {
                        if (undoStack.size > 0 && valueStack.size > 0) {
                            val lastResult = valueStack.pop()
                            val lastCommand = undoStack.pop()
                            if (lastCommand != null) {
                                lastCommand.unExecute(lastResult)
                                valueStack.push(calculator.getResult())
                                val reverseOperand = calculator.getReverseOperand() ?: throw CalculatorException("Reverse operand is missing")
                                valueStack.push(reverseOperand)
                            }
                        } else {
                            throw CalculatorException("There is no more to undo!")
                        }
                    }
                    else -> {
                        val map = ArithmeticOperation.arithmeticOperationMap()
                        val operation = map[token] ?: throw CalculatorException("Available operators are +, -, *, /, sqrt, undo, clear.")
                        val numOperands = operation.operandNumber
                        if (numOperands > valueStack.size) {
                            throwOperatorException(operation.symbol, positionIndex)
                        }
                        val firstOperand = valueStack.pop().toDouble()
                        val secondOperand = if (numOperands > 1) valueStack.pop().toDouble() else null
                        val command = CalculatorCommand(calculator, token, firstOperand, secondOperand)
                        command.execute()
                        valueStack.push(calculator.getResult())
                        undoStack.push(command)
                    }
                }
            }
            positionIndex++
        }
    }

    fun printStack() {
        val format = DecimalFormat("0.##########")
        print("stack: ")
        valueStack.map { print(format.format(it)+" ") }
        println()
    }

    private fun throwOperatorException(operator: String, positionIndex: Int) {
        val msg = String.format("operator %s (position: %d): insufficient parameters", operator, positionIndex)
        throw CalculatorException(msg)
    }
}

