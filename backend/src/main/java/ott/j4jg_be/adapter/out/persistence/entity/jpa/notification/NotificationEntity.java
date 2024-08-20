package ott.j4jg_be.adapter.out.persistence.entity.jpa.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MatchingEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @Column(name = "message")
    private String message;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id", insertable = false, updatable = false)
    private MatchingEntity matching;

    @Column(name = "matching_id")
    private int matchingId;

    @Column(name = "status")
    private boolean status;

    protected NotificationEntity(){}

    public NotificationEntity(String message, int matchingId){
        this.message = message;
        this.matchingId = matchingId;
        this.status = false;
    }

}
