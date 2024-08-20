package ott.j4jg_be.adapter.out.persistence.jpa.adapter;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.jpa.entity.NotificationEntity;
import ott.j4jg_be.adapter.out.persistence.jpa.repository.NotificationRepository;
import ott.j4jg_be.adapter.out.persistence.mapper.NotificationMapper;
import ott.j4jg_be.application.port.out.NotificationPort;
import ott.j4jg_be.domain.Notification;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationAdapter implements NotificationPort {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper mapper;

    public NotificationAdapter(NotificationRepository notificationRepository,
                               NotificationMapper mapper){
        this.notificationRepository = notificationRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Notification> getNotificationList() {

        List<NotificationEntity> notificationEntityList = notificationRepository.findByStatus(false);

        return notificationEntityList.stream().map(mapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public void updateNotification(int notificationId) {
        NotificationEntity entity = notificationRepository.findById(notificationId).orElse(null);

        if(entity != null){
            entity.updateStatusToTrue();
            notificationRepository.save(entity);
        }
    }
}
