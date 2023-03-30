package com.example.calculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlin.math.pow

class CalculatorViewModel : ViewModel() {
    private data class State(
        val num1: String = "",
        val num2: String = "",
        val operator: Operators? = null,
    )
    fun Double.roundToString() = "%.10f".format(this).trimEnd('0').trimEnd('.')
    class ViewState(val result: String)

    private val state: MutableStateFlow<State> = MutableStateFlow(State())
    internal val viewState = state.map { state ->


        val number1 = state.num1.ifEmpty { "0" }
        val number2 = state.num2
        val operator = state.operator?.symbol.orEmpty()
        val num1 = number1.toDoubleOrNull()?.roundToString() ?: number1
        val num2 = number2.toDoubleOrNull()?.roundToString() ?: number2
        ViewState("$num1 $operator $num2")
    }

    fun dispatch(action: ActionButton) {
        when (action) {
            is ActionButton.Number -> onNumber(action.number)
            is ActionButton.Operator -> onOperator(action.operator)
            is ActionButton.Clear -> onClear()
            is ActionButton.Delete -> onDelete()
            is ActionButton.Calculate -> onCalculate()
            is ActionButton.Percentage -> onPercentage()
            is ActionButton.Decimal -> onDecimal()
        }
    }
    private fun onPercentage() {
        val currentState = state.value
        if (currentState.operator == null) {
            val num1 = currentState.num1.toDoubleOrNull() ?: 0.0
            state.value = currentState.copy(num1 = (num1 / 100).toString())
        } else {
            val num2 = currentState.num2.toDoubleOrNull() ?: 0.0
            state.value = currentState.copy(num2 = (num2 / 100).toString())
        }
    }

    private fun onOperator(operator: Operators) {
        val currentState = state.value
        if (currentState.num1.isNotEmpty() && currentState.operator == null) {
            state.value = currentState.copy(operator = operator)
        }
    }


    private fun onDelete() {
        val currentState = state.value

        if (currentState.operator == null) {
            state.value = currentState.copy(num1 = currentState.num1.dropLast(1))
        } else if (currentState.num2.isEmpty()) {
            state.value = currentState.copy(operator = null)
        } else {
            state.value = currentState.copy(num2 = currentState.num2.dropLast(1))
        }
    }

    private fun onDecimal() {
        val currentSate = state.value
        if (currentSate.operator == null &&
            !currentSate.num1.contains(".") &&
            currentSate.num2.isEmpty()
        ) {
            state.value = currentSate.copy(num1 = currentSate.num1 + ".")
        } else if (currentSate.operator != null &&
            !currentSate.num2.contains(".") &&
            currentSate.num2.isNotEmpty()
        ) {
            state.value = currentSate.copy(num2 = currentSate.num2 + ".")
        }
    }


    private fun onClear() {
        val currentState = state.value
        state.value = currentState.copy(num1 = "0", num2 = "", operator = null)
    }

    private fun onCalculate() {
        val currentState = state.value
        if (currentState.num1.isNotEmpty() && currentState.num2.isNotEmpty() && currentState.operator != null) {
            val num1 = currentState.num1.toDouble()
            val num2 = currentState.num2.toDouble()
            val result = when (currentState.operator) {
                Operators.Add -> num1 + num2
                Operators.Subtract -> num1 - num2
                Operators.Multiply -> num1 * num2
                Operators.Divide -> num1 / num2
                Operators.Power -> num1.pow(num2)
            }
            state.value = currentState.copy(num1 = result.toString(), num2 = "", operator = null)
        }
    }

    private fun onNumber(number: Int) {
        val currentState = state.value
        if (currentState.operator == null) {
            if (currentState.num1 == "0") {
                state.value = currentState.copy(num1 = number.toString())
            } else {
                val num1 = currentState.num1 + number
                state.value = currentState.copy(num1 = num1)
            }
        } else {
            val num2 = currentState.num2 + number
            state.value = currentState.copy(num2 = num2)
        }
    }
}