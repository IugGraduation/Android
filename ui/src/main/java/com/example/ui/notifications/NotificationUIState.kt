package com.example.ui.notifications

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.domain.model.Notification
import com.example.domain.model.UiState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class NotificationUIState(
    val notifications: List<Notification> = listOf(),

    override val isLoading: Boolean = false,
    override val error: String? = null
) : UiState {
    @RequiresApi(Build.VERSION_CODES.O)
    fun groupNotifications(): List<NotificationGroup> {
        return notifications.groupBy { notification ->
            when {
                notification.date.isEqual(LocalDate.now()) -> "Today"
                notification.date.isEqual(LocalDate.now().minusDays(1)) -> "Yesterday"
                else -> notification.date.format(DateTimeFormatter.ofPattern("MMM d"))
            }
        }.map { (title, notifications) ->
            NotificationGroup(title, notifications)
        }
    }

}

data class NotificationGroup(
    val title: String = "",
    val notifications: List<Notification> = listOf()
)

