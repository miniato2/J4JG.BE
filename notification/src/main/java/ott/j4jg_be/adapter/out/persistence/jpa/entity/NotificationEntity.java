package ott.j4jg_be.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "matching_id")
    private MatchingEntity matching;

    @Column(name = "status")
    private boolean status;

    protected NotificationEntity(){}


    public void updateStatusToTrue(){
        this.status = true;
    }

}