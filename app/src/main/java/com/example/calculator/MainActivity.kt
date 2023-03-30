package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ui.CalcButtonComponent
import com.example.calculator.ui.InputDisplayComponent
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.spacing

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.viewState.collectAsState(
                    initial = CalculatorViewModel.ViewState("0")
                ).value
                ScreenCalculator(state) {
                    viewModel.dispatch(it)
                }
            }
        }
    }
}


@Composable
fun ScreenCalculator(state: CalculatorViewModel.ViewState, dispatcher: (ActionButton) -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.lg,
                    vertical = MaterialTheme.spacing.sm
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                InputDisplayComponent(state)
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.lg))
                CalculatorButtonGridLayout(dispatcher)
            }
        }
    }
}

@Composable
private fun CalculatorButtonGridLayout(dispatcher: (ActionButton) -> Unit) {
    val buttons = listOf(
        ActionButton.Clear,
        ActionButton.Operator(Operators.Power),
        ActionButton.Percentage,
        ActionButton.Operator(Operators.Divide),
        ActionButton.Number(7),
        ActionButton.Number(8),
        ActionButton.Number(9),
        ActionButton.Operator(Operators.Multiply),
        ActionButton.Number(4),
        ActionButton.Number(5),
        ActionButton.Number(6),
        ActionButton.Operator(Operators.Subtract),
        ActionButton.Number(1),
        ActionButton.Number(2),
        ActionButton.Number(3),
        ActionButton.Operator(Operators.Add),
        ActionButton.Number(0),
        ActionButton.Decimal,
        ActionButton.Delete,
        ActionButton.Calculate
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm),
        content = {
            items(buttons) {
                CalcButtonComponent(
                    modifier = Modifier.aspectRatio(1f),
                    color = it.butonColor,
                    symbols = it.symbol
                ) {
                    dispatcher(it)
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorTheme {
        ScreenCalculator(CalculatorViewModel.ViewState("0")) {  }
    }
}