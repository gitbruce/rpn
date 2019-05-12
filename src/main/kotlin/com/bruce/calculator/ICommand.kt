package com.bruce.calculator

interface Command {
    fun execute()
    fun unExecute(withResult: Double)
}