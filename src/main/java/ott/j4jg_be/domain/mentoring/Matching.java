package ott.j4jg_be.domain.mentoring;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Matching {
    private int applicationId;
    private int mentoringId;
    private Long userId;
    private String userName;
    private String surveyResponse;
    private LocalDateTime createdAt;
    private boolean status;

    public Matching(int applicationId, int mentoringId, Long userId,
                                LocalDateTime createdAt, boolean status){
        this.applicationId = applicationId;
        this.mentoringId = mentoringId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.status = status;
    }

}
