package ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "matching")
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
public class MatchingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id")
    private int matchingId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "mentoring_id", nullable = false)
    private int mentoringId;

    @Column(name = "status")
    private boolean status;

    @CreatedDate
    @Column(name = "crated_at")
    private LocalDateTime createdAt;

    protected MatchingEntity(){}

    public MatchingEntity(String userId, int mentoringId){
        this.userId = userId;
        this.mentoringId = mentoringId;
        this.status = true;
    }


}
