package ott.j4jg_be.application.port.out.mentoring;

import ott.j4jg_be.domain.mentoring.Matching;

public interface MatchingPort {
    Matching matching(String userId, int mentoringId);
}
