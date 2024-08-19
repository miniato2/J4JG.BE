package ott.j4jg_be.adapter.out.persistence.entity.jpa.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@AllArgsConstructor
public class NotificationEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;

    @Column
    private String message;

    @Column
    private LocalDateTime createdAt;

    @Column
    private int matchingId;

    protected NotificationEntity(){}

    public NotificationEntity(String message, int matchingId){
        this.message = message;
        this.matchingId = matchingId;
    }

}
