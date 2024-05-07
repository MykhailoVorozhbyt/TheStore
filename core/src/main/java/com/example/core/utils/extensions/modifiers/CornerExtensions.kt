package com.example.core.utils.extensions.modifiers

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


fun baseTopStartCornerShape() = RoundedCornerShape(topStart = 15.dp)
fun baseTopEndCornerShape() = RoundedCornerShape(topEnd = 15.dp)

fun Modifier.baseTopStartCornerShape() = this.clip(RoundedCornerShape(topStart = 15.dp))
fun Modifier.baseTopEndCornerShape() = this.clip(RoundedCornerShape(topEnd = 15.dp))

fun baseBottomStartCornerShape() = RoundedCornerShape(bottomStart = 15.dp)
fun baseBottomEndCornerShape() = RoundedCornerShape(bottomEnd = 15.dp)

fun Modifier.baseBottomStartCornerShape() = this.clip(RoundedCornerShape(bottomStart = 15.dp))
fun Modifier.baseBottomEndCornerShape() = this.clip(RoundedCornerShape(bottomEnd = 15.dp))

fun baseTopRoundedCornerShape() = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
fun baseBottomRoundedCornerShape() = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)

fun Modifier.baseTopRoundedCornerShape() =  this.clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
fun Modifier.baseBottomRoundedCornerShape() =  this.clip(RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))


fun baseRoundedCornerShape() = RoundedCornerShape(15.dp)

fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.08f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}