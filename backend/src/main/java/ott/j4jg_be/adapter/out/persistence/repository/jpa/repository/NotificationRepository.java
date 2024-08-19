package ott.j4jg_be.adapter.out.persistence.repository.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.notification.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer> {
}
