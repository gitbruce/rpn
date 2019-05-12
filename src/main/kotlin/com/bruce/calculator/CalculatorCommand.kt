package com.bruce.calculator

class CalculatorCommand(val calculator: Calculator,
                        var operator: String, val operand1: Double, var operand2: Double?) : Command {

    override fun execute() {
        calculator.execute(operator, operand1, operand2)
    }

    override fun unExecute(withResult: Double) {
        val opposite = undo(operator)
        calculator.execute(opposite, operand1, withResult)
    }

    private fun undo(operator: String): String {
        val map = ArithmeticOperation.arithmeticOperationMap()
        return map[operator]?.opposite ?: throw CalculatorException("undo operation not found")
    }
}