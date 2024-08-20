package ott.j4jg_be.application.port.out;

import ott.j4jg_be.domain.Notification;

import java.util.List;

public interface NotificationPort {

    List<Notification> getNotificationList();

    void updateNotification(int notificationId);
}
