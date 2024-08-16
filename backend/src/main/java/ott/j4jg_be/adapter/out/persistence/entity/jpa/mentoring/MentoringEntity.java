package ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.user.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentoring")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
@Builder
public class MentoringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentoring_id")
    private int mentoringId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "title", nullable = false)
    private String title;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    private String endDate;

    @Column(name = "level", nullable = false)
    private String level;

    @Column(name = "point")
    private int point;

    @Column(name = "skill_stack", columnDefinition = "json", nullable = false)
    private String skillStack;

    @Column(name = "week", columnDefinition = "json", nullable = false)
    private String week;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "max_person", nullable = false)
    private int maxPerson;

    @Column(name = "current_person")
    private int currentPerson;

    @Column(name = "status")
    private boolean status;

    protected MentoringEntity(){}

    public void updateCurrentPerson(){
        this.currentPerson += 1;
    }

    public void updateStatusToFalse(){
        this.status = false;
    }
}
