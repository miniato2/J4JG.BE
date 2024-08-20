package ott.j4jg_be.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.jpa.entity.NotificationEntity;
import ott.j4jg_be.domain.Notification;

@Component
public class NotificationMapper {

    public Notification mapToDomain(NotificationEntity notificationEntity){

        return new Notification(
                notificationEntity.getNotificationId(),
                notificationEntity.getMatching().getUser().getUserAddInfo().getUserNickname(),
                notificationEntity.getMatching().getUser().getUserEmail(),
                notificationEntity.getMatching().getUser().getUserPhoneNumber()
        );
    }
}
