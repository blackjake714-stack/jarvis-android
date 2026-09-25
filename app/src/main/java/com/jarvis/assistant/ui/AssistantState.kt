package com.jarvis.assistant.ui

enum class AssistantState {
    IDLE,
    LISTENING,
    THINKING,
    SPEAKING
}

data class SystemStatus(
    val batteryPercent: Int = 100,
    val isCharging: Boolean = false,
    val wifiConnected: Boolean = false,
    val bluetoothOn: Boolean = false,
    val wakeWordEnabled: Boolean = true,
    val aiProviderConfigured: Boolean = false
)
