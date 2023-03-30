package com.example.calculator.ui

import android.graphics.BlurMaskFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.shadow(
    color: Color = Color.Black,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
) = then(
    drawBehind {
        drawIntoCanvas { canvas ->
            val leftPixels = offsetX.toPx()
            val topPixels = offsetY.toPx()
            val rightPixels = size.width + topPixels
            val bottomPixels = size.height + leftPixels

            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            if(blurRadius != 0.dp){
                frameworkPaint.maskFilter = (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            canvas.drawRect(
                left = leftPixels,
                top = topPixels,
                right = rightPixels,
                bottom = bottomPixels,
                paint = paint,
            )
        }
    }
)