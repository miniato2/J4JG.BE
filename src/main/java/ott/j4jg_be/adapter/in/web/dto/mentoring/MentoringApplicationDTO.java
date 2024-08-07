package ott.j4jg_be.adapter.in.web.dto.mentoring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MentoringApplicationDTO {
    private int applicationId;
    private Long userId;
    private String userName;
    private String surveyResponse;
    private LocalDateTime createdAt;
//    private boolean status;

    public MentoringApplicationDTO(int applicationId, Long userId, LocalDateTime createdAt){
        this.applicationId = applicationId;
        this.userId = userId;
        this.createdAt = createdAt;
    }


}
