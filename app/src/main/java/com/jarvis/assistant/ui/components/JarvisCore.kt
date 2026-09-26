package com.jarvis.assistant.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.jarvis.assistant.ui.AssistantState
import com.jarvis.assistant.ui.theme.JarvisAmberAlert
import com.jarvis.assistant.ui.theme.JarvisCyan
import com.jarvis.assistant.ui.theme.JarvisCyanDim
import kotlin.math.sin

@Composable
fun JarvisCore(
    state: AssistantState,
    amplitude: Float = 0f,
    modifier: Modifier = Modifier
) {
    val infinite = rememberInfiniteTransition(label = "jarvis-core")

    val speed = when (state) {
        AssistantState.IDLE -> 6000
        AssistantState.LISTENING -> 2200
        AssistantState.THINKING -> 900
        AssistantState.SPEAKING -> 1500
    }

    val rotation by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(speed, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation"
    )

    val pulse by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(if (state == AssistantState.THINKING) 500 else 1400, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    val ringColor = if (state == AssistantState.THINKING) JarvisAmberAlert else JarvisCyan

    Canvas(modifier = modifier.size(220.dp)) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val baseRadius = size.minDimension / 2f * 0.6f

        rotate(rotation) {
            drawCircle(
                color = JarvisCyanDim,
                radius = baseRadius + 26f,
                center = center,
                style = Stroke(width = 2f)
            )
        }

        val reactiveOffset = when (state) {
            AssistantState.LISTENING -> amplitude * 22f
            AssistantState.SPEAKING -> (sin(pulse * Math.PI).toFloat()) * 16f
            AssistantState.THINKING -> pulse * 10f
            AssistantState.IDLE -> 0f
        }
        drawCircle(
            color = ringColor,
            radius = baseRadius + reactiveOffset,
            center = center,
            style = Stroke(width = 3f)
        )

        val coreAlpha = when (state) {
            AssistantState.IDLE -> 0.35f
            AssistantState.LISTENING -> 0.55f + amplitude * 0.4f
            AssistantState.THINKING -> 0.5f + pulse * 0.3f
            AssistantState.SPEAKING -> 0.6f + pulse * 0.3f
        }
        drawCircle(
            color = ringColor.copy(alpha = coreAlpha.coerceIn(0f, 1f)),
            radius = baseRadius * 0.55f,
            center = center
        )
    }
}
