package main.com.veontomo.rearrange

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

/**
 * Show notifications inside IDE
 * @param origin marker for the messages.
 */
class Notification(private val origin: String) {

    private val NOTIFICATION_GROUP = NotificationGroup("Rearrange", NotificationDisplayType.NONE, true)

    /**
     * Display notification in the IDE window
     * @param msg text to display
     */
    fun notify(msg: String) {
        val txt = if (msg.isEmpty()) "(no message)" else msg
        val notification = NOTIFICATION_GROUP.createNotification("$origin: $txt", NotificationType.INFORMATION);
        Notifications.Bus.notify(notification)
    }
}