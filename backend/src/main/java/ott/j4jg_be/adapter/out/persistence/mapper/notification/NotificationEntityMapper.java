package ott.j4jg_be.adapter.out.persistence.mapper.notification;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.notification.NotificationEntity;
import ott.j4jg_be.domain.notification.Notification;

@Component
public class NotificationEntityMapper {

    public NotificationEntity mapToEntity(Notification notification){
        return new NotificationEntity(
                notification.getMessage(),
                notification.getMatchingId()
        );

    }
}
