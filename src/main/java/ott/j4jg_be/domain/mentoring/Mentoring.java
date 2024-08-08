package ott.j4jg_be.domain.mentoring;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Mentoring {

    private int mentoringId;
    private Long userId;
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
    private int maxPerson;  // 최대 인원
    private int currentPerson; //현재 참여인원
    private boolean status;

    public boolean isNotFull(int maxPerson, int currentPerson) {
        return currentPerson < maxPerson;
    }


}
