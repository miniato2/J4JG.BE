package ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentoring_application")
@EntityListeners(AuditingEntityListener.class)
@Getter
@AllArgsConstructor
public class MentoringApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;
    //연관관계 필요
    private String userId;
    private boolean status; //초기 true, 신청됬을때 false
    @CreatedDate
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
