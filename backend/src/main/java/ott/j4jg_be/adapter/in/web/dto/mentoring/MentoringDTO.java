package ott.j4jg_be.adapter.in.web.dto.mentoring;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentoringDTO {
    private int mentoringId;
    private String userId;
    private String userName;
    private String description;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String startDate;
    private String endDate;
    private String level; //초급, 중급, 상급
    private int point;
    private String[] skillStack; //코드로 or 문자로
    private String[] week; //요일
    private String type; //one, team, any
    private int maxPerson;
    private int currentPerson;
    private boolean status;
}
