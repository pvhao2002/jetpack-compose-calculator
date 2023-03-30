package com.example.calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.ui.theme.ButtonPink
import com.example.calculator.ui.theme.ButtonShadowColorBottom
import com.example.calculator.ui.theme.ButtonShadowColorTop
import com.example.calculator.ui.theme.CalculatorTheme

@Composable
internal fun CalcButtonComponent(
    modifier: Modifier = Modifier,
    color: Color,
    symbols: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(color)
            .clickable { onClick() }
            .then(modifier)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize(fraction = 0.8f)
                .shadow(
                    color = ButtonShadowColorTop,
                    offsetX = (-4).dp,
                    offsetY = (-4).dp,
                    blurRadius = 8.dp
                )
                .shadow(
                    color = ButtonShadowColorBottom,
                    offsetX = 4.dp,
                    offsetY = 4.dp,
                    blurRadius = 8.dp
                )
                .clip(MaterialTheme.shapes.medium)
                .background(color)
        ) {
            Text(
                text = symbols,
                fontSize = 44.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CalcButtonComponentPreview() {
    CalculatorTheme {
        CalcButtonComponent(
            modifier = Modifier.size(100.dp),
            color = ButtonPink,
            symbols = "1",
            onClick = {}
        )
    }
}
