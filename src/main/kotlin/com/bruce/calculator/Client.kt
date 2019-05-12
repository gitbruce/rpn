package com.bruce.calculator

fun main(args : Array<String>) {

    val rpn = RpnInvoker()
    println("RPN Calculator, please input your string, please enter 'q' to quit")
    var stringInput = readLine()!!
    while (stringInput != "q") {
        try {
            rpn.parse(stringInput)
        } catch (e: CalculatorException) {
            println(e.message)
        }
        rpn.printStack()
        stringInput = readLine()!!
    }
}