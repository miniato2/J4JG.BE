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

    @PostMapping("/matching")
    public void mentoringMatching(@ModelAttribute MatchingRequestDTO matchingRequestDTO){
        matchingUsecase.matching(matchingRequestDTO);
    }
}
