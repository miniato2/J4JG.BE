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
    private Long userId;
    private boolean status;
    @CreatedDate
    private LocalDateTime createAt;

    protected MentoringApplicationEntity(){}

    public MentoringApplicationEntity(Long userId){
        this.userId = userId;
        this.status = false;
    }

    public void updateStatusToTrue(){
        this.status = true;
    }
}
