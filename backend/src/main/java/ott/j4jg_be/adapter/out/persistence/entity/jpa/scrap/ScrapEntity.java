package ott.j4jg_be.adapter.out.persistence.entity.jpa.scrap;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "scrap", indexes = {
        @Index(name = "idx_user_job", columnList = "user_id, jobinfo_id")
})
@Getter
@AllArgsConstructor
public class ScrapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private int scrapId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "jobinfo_id")
    private int jobInfoId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "status")
    private Boolean status;

    protected ScrapEntity(){}

    public ScrapEntity(String userId,
                       int jobInfoId,
                       boolean status){
        this.userId = userId;
        this.jobInfoId = jobInfoId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public void updateStatus(boolean newStatus){
        this.status = newStatus;
    }
}
