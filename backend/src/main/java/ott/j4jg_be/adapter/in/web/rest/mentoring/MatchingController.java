package ott.j4jg_be.adapter.in.web.rest.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MatchingRequestDTO;
import ott.j4jg_be.application.port.in.mentoring.MatchingUsecase;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingUsecase matchingUsecase;

    /**
     * @api {post} /matching 멘토링 매칭
     * @apiName mentoringMatching
     * @apiGroup Mentoring Matching
     *
     * @apiBody {Number} mentoringId 멘토링 ID.
     * @apiBody {String} userId 사용자 ID.
     * @apiBody {Number} applicationId 지원서 ID.
     */
    @PostMapping("/matching")
    public void mentoringMatching(@ModelAttribute MatchingRequestDTO matchingRequestDTO){
        matchingUsecase.matching(matchingRequestDTO);
    }

    //매칭 조회
}
