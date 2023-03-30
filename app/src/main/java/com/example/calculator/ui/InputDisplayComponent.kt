package com.example.calculator.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.CalculatorViewModel
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.ResultShadowColorBottom
import com.example.calculator.ui.theme.ResultShadowColorTop
import com.example.calculator.ui.theme.spacing

@Composable
internal fun InputDisplayComponent(state: CalculatorViewModel.ViewState) {
    Box(
        Modifier
            .fillMaxWidth()
            .shadow(
                color = ResultShadowColorTop,
                blurRadius = 15.dp,
                offsetX = (-6).dp,
                offsetY = (-6).dp
            )
            .shadow(
                color = ResultShadowColorBottom,
                blurRadius = 15.dp,
                offsetX = 6.dp,
                offsetY = 6.dp
            )
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.onBackground)
            .padding(
                horizontal = MaterialTheme.spacing.md,
                vertical = MaterialTheme.spacing.sm
            )

    ) {
        Text(
            text = state.result,
            color = Color.White,
            fontSize = 44.sp,
            overflow = TextOverflow.Visible,
            maxLines = 1,
            style = MaterialTheme.typography.h1.merge(TextStyle(textAlign = TextAlign.End)),
            modifier = Modifier.fillMaxWidth()
        )
    }

}


@Preview(showBackground = true)
@Composable
fun InputDisplayComponentPreview() {
    CalculatorTheme {
        Box(modifier = Modifier.padding(10.dp)) {
            InputDisplayComponent(CalculatorViewModel.ViewState("1 + 2 = 3"))
        }
    }
}