package ott.j4jg_be.domain.mentoring;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MentoringApplication {
    //멘토링 신청
    private int applicationId;
    private String userId;
    private String userName;
    private String surveyResponse;
    private LocalDateTime createdAt;
    private boolean status;

    public MentoringApplication(int applicationId, String userId,
                                LocalDateTime createdAt, boolean status){
        this.applicationId = applicationId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.status = status;
    }

}
