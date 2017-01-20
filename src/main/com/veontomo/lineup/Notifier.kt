package com.veontomo.lineup

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

/**
 * Show notifications inside IDE
 * @param origin marker for the messages.
 */
class Notifier(private val origin: String) {

    private val GROUP_NAME = "Rearrange"

    private val NOTIFICATION_GROUP = NotificationGroup(GROUP_NAME, NotificationDisplayType.NONE, true)

    /**
     * Display a message in the tool window bar.
     *
     * The origin of the message is to be prepended to the message.
     *
     * @param msg text to display
     */
    fun notify(msg: String) {
        val txt = if (msg.isEmpty()) "(no message)" else msg
        val notification = NOTIFICATION_GROUP.createNotification("$origin: $txt", NotificationType.INFORMATION)
        Notifications.Bus.notify(notification)
    }
}