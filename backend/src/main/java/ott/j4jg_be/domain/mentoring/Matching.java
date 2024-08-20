package ott.j4jg_be.domain.mentoring;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Matching {
    private int matchingId;
    private int mentoringId;
    private String userId;
    private String userName;
    private String email;
    private String phone;
    private String surveyResponse;
    private LocalDateTime createdAt;
    private boolean status;


}
