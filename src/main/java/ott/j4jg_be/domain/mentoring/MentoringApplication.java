package ott.j4jg_be.domain.mentoring;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MentoringApplication {
    //멘토링 신청
    private int applicationId;
    private Long userId;
    private String userName;
    private String surveyResponse;
    private LocalDateTime createdAt;
    private boolean status;

    public MentoringApplication(int applicationId, Long userId,
                                LocalDateTime createdAt, boolean status){
        this.applicationId = applicationId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.status = status;
    }

}
