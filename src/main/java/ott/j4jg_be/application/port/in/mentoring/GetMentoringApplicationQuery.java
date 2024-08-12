package ott.j4jg_be.application.port.in.mentoring;

import org.springframework.data.domain.Page;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringApplicationDTO;

public interface GetMentoringApplicationQuery {

    Page<MentoringApplicationDTO> getApplicationList(int page);
}
