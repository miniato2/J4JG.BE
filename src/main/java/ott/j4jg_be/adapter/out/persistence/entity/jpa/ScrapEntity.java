package ott.j4jg_be.adapter.out.persistence.entity.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "scrap")
@Getter
@AllArgsConstructor
public class ScrapEntity {
    @Id
    @Column(name = "scrap_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scrapId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "jobinfo_id")
    private int jobInfoId;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "status")
    private Boolean status;

    // + 상태 true or false

    protected ScrapEntity(){}

    public ScrapEntity(String userId,
                       int jobInfoId,
                       boolean status){
        this.userId = userId;
        this.jobInfoId = jobInfoId;
        this.createdAt = LocalDateTime.now();
        this.status = status;
    }

    public void updateStatus(boolean newStatus){
        this.status = newStatus;
    }
}
