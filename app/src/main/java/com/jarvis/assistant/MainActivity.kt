package com.jarvis.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.jarvis.assistant.ui.AssistantState
import com.jarvis.assistant.ui.SystemStatus
import com.jarvis.assistant.ui.screens.HomeScreen
import com.jarvis.assistant.ui.theme.JarvisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JarvisTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var state by remember { mutableStateOf(AssistantState.IDLE) }
                    val status = remember {
                        SystemStatus(
                            batteryPercent = 100,
                            wifiConnected = true,
                            wakeWordEnabled = true,
                            aiProviderConfigured = false
                        )
                    }
                    HomeScreen(
                        state = state,
                        status = status,
                        lastHeardText = "",
                        lastReplyText = if (!status.aiProviderConfigured)
                            "AI provider not configured yet — set an API key in Settings." else ""
                    )
                }
            }
        }
    }
}
