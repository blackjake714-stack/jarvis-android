package com.jarvis.assistant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jarvis.assistant.ui.AssistantState
import com.jarvis.assistant.ui.SystemStatus
import com.jarvis.assistant.ui.components.JarvisCore
import com.jarvis.assistant.ui.theme.JarvisBackground
import com.jarvis.assistant.ui.theme.JarvisCyan
import com.jarvis.assistant.ui.theme.JarvisPanelBorder
import com.jarvis.assistant.ui.theme.JarvisTextSecondary

@Composable
fun HomeScreen(
    state: AssistantState,
    status: SystemStatus,
    lastHeardText: String,
    lastReplyText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(JarvisBackground)
            .padding(20.dp)
    ) {
        StatusBar(status)

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                JarvisCore(state = state)
                Spacer(Modifier.height(24.dp))
                Text(
                    text = stateLabel(state),
                    style = MaterialTheme.typography.titleMedium,
                    color = JarvisCyan
                )
            }
        }

        TranscriptPanel(lastHeardText = lastHeardText, lastReplyText = lastReplyText)
    }
}

private fun stateLabel(state: AssistantState): String = when (state) {
    AssistantState.IDLE -> "STANDING BY"
    AssistantState.LISTENING -> "LISTENING"
    AssistantState.THINKING -> "PROCESSING"
    AssistantState.SPEAKING -> "SPEAKING"
}

@Composable
private fun StatusBar(status: SystemStatus) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "JARVIS",
            style = MaterialTheme.typography.headlineLarge,
            color = JarvisCyan
        )
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "BATT ${status.batteryPercent}%${if (status.isCharging) " ⚡" else ""}",
                style = MaterialTheme.typography.labelSmall,
                color = JarvisTextSecondary
            )
            Text(
                text = if (status.wifiConnected) "WIFI ONLINE" else "WIFI OFFLINE",
                style = MaterialTheme.typography.labelSmall,
                color = JarvisTextSecondary
            )
            Text(
                text = if (status.wakeWordEnabled) "WAKE-WORD ARMED" else "WAKE-WORD OFF",
                style = MaterialTheme.typography.labelSmall,
                color = if (status.wakeWordEnabled) JarvisCyan else Color(0xFF8A4B4B)
            )
        }
    }
}

@Composable
private fun TranscriptPanel(lastHeardText: String, lastReplyText: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, JarvisPanelBorder, RoundedCornerShape(8.dp))
            .padding(14.dp)
    ) {
        if (lastHeardText.isNotBlank()) {
            Text("HEARD (HE)", style = MaterialTheme.typography.labelSmall, color = JarvisTextSecondary)
            Text(lastHeardText, style = MaterialTheme.typography.bodyMedium, color = Color.White)
            Spacer(Modifier.height(8.dp))
        }
        if (lastReplyText.isNotBlank()) {
            Text("JARVIS (EN)", style = MaterialTheme.typography.labelSmall, color = JarvisTextSecondary)
            Text(lastReplyText, style = MaterialTheme.typography.bodyMedium, color = JarvisCyan)
        }
        if (lastHeardText.isBlank() && lastReplyText.isBlank()) {
            Text(
                "Say \"Hey JARVIS\" to begin.",
                style = MaterialTheme.typography.bodyMedium,
                color = JarvisTextSecondary
            )
        }
    }
}
