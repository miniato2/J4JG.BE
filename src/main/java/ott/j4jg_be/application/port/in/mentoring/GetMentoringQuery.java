package ott.j4jg_be.application.port.in.mentoring;

import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;

import java.util.List;

public interface GetMentoringQuery {
    List<MentoringDTO> getMentoringList();
    MentoringDTO getMentoring(int mentoringId);
}
