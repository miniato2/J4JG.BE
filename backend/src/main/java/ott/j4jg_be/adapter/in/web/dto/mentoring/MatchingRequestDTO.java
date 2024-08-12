package ott.j4jg_be.adapter.in.web.dto.mentoring;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MatchingRequestDTO {
    private Long userId;
    private int mentoringId;
    private int applicationId;
}
