package ott.j4jg_be.adapter.out.persistence.adapter.jpa.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.mapper.notification.NotificationEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.repository.NotificationRepository;
import ott.j4jg_be.application.port.out.notification.CreateNotificationPort;
import ott.j4jg_be.domain.notification.Notification;

@Component
@RequiredArgsConstructor
public class CreateNotificationAdapter implements CreateNotificationPort {

    private final NotificationRepository notificationRepository;
    private final NotificationEntityMapper mapper;

    @Override
    public void createNotification(Notification notification) {

        notificationRepository.save(mapper.mapToEntity(notification));
    }
}
