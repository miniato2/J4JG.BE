package ott.j4jg_be.application.port.in.mentoring;

import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringApplicationDTO;

import java.util.List;

public interface GetMentoringApplicationQuery {

    List<MentoringApplicationDTO> getApplicationList();
}
