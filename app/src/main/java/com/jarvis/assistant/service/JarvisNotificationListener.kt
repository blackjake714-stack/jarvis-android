package com.jarvis.assistant.service

import android.service.notification.StatusBarNotification
import android.service.notification.NotificationListenerService

class JarvisNotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.packageName != WHATSAPP_PACKAGE) return
    }

    companion object {
        const val WHATSAPP_PACKAGE = "com.whatsapp"
    }
}
