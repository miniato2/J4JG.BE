package ott.j4jg_be.adapter.in.web.dto.mentoring;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MatchingRequestDTO {
    private int mentoringId;
    private String userId;
    private int applicationId;
}
