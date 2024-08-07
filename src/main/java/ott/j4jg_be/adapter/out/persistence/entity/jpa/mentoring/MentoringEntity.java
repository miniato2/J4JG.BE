package ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentoring")
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Getter
public class MentoringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mentoringId;
    private Long userId;
    private String description;
    private String title;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private String startDate;
    private String endDate;
    private String level;
    private int point;
    @Column(columnDefinition = "json")
    private String skillStack;
    @Column(columnDefinition = "json")
    private String week;
    private String type;
    private int maxPerson;
    private int currentPerson;
    private boolean status;

    protected MentoringEntity(){}

    public void updateCurrentPerson(){
        this.currentPerson += 1;
    }
}
