package ott.j4jg_be.application.port.in.mentoring;

import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;

public interface CreateMentoringUsecase {
    void createMentoring(MentoringDTO mentoringDTO);
}
