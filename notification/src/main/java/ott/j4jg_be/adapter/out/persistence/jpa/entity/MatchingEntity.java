package ott.j4jg_be.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "matching")
@Getter
public class MatchingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id")
    private int matchingId;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "mentoring_id", nullable = false)
    private int mentoringId;

    @Column(name = "status")
    private boolean status;

    @CreatedDate
    @Column(name = "crated_at")
    private LocalDateTime createdAt;

}
