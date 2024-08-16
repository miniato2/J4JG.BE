package ott.j4jg_be.application.port.in.mentoring;

import ott.j4jg_be.adapter.in.web.dto.mentoring.MatchingRequestDTO;

public interface MatchingUsecase {
    void matching(MatchingRequestDTO matchingRequestDTO);
}
