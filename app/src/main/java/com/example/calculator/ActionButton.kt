package com.example.calculator

import androidx.compose.ui.graphics.Color
import com.example.calculator.ui.theme.ButtonBlue
import com.example.calculator.ui.theme.ButtonPink
import com.example.calculator.ui.theme.ButtonYellow

sealed class ActionButton(val symbol: String, val butonColor: Color) {

    data class Number(val number: Int) : ActionButton(number.toString(), ButtonBlue)
    data class Operator(val operator: Operators) : ActionButton(operator.symbol, ButtonPink)

    object Clear : ActionButton("AC", ButtonPink)
    object Delete : ActionButton("⇽", ButtonBlue)
    object Calculate : ActionButton("=", ButtonYellow)
    object Percentage : ActionButton("%", ButtonPink)
    object Decimal : ActionButton(".", ButtonBlue)
}

sealed class Operators(val symbol: String) {
    object Add : Operators("+")
    object Subtract : Operators("-")
    object Multiply : Operators("x")
    object Divide : Operators("/")
    object Power : Operators("^")
}