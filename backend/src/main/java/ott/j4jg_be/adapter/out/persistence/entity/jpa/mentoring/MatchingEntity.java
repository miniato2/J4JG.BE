package ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "matching")
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
public class MatchingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchingId;
    private String userId;
    private int mentoringId;
    private boolean status;
    @CreatedDate
    private LocalDateTime createdAt;

    protected MatchingEntity(){}

    public MatchingEntity(String userId, int mentoringId){
        this.userId = userId;
        this.mentoringId = mentoringId;
        this.status = true;
    }


}
