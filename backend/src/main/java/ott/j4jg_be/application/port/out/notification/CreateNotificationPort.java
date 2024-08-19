package ott.j4jg_be.application.port.out.notification;

import ott.j4jg_be.domain.notification.Notification;

public interface CreateNotificationPort {

    void createNotification(Notification notification);
}
