package ott.j4jg_be.application.port.in.mentoring;

import org.springframework.data.domain.Page;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;

import java.util.List;

public interface GetMentoringQuery {
    Page<MentoringDTO> getMentoringList(int page);
    MentoringDTO getMentoring(int mentoringId);
    Page<MentoringDTO> getMyMentoring(String userId, int page, String status);
}
