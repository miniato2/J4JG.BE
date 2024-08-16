package ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.user.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentoring_application")
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
public class MentoringApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "status")
    private boolean status; //초기 true, 신청됬을때 false

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createAt;

    protected MentoringApplicationEntity(){}

    public MentoringApplicationEntity(String userId){
        this.userId = userId;
        this.status = true;
    }

    public void updateStatus(boolean status){
        this.status = !status;
    }
}
