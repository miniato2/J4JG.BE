package ott.j4jg_be.application.port.out.mentoring;

import org.springframework.data.domain.Page;
import ott.j4jg_be.domain.mentoring.Mentoring;

import java.util.List;

public interface GetMentoringPort {
    Page<Mentoring> getMentoringList(int page);

    Mentoring getMentoring(int mentoringId);

    Page<Mentoring> getMyMentoring(String userId, int page, String status);
}
