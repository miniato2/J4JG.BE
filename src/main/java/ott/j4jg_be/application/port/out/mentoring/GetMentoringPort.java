package ott.j4jg_be.application.port.out.mentoring;

import ott.j4jg_be.domain.mentoring.Mentoring;

import java.util.List;

public interface GetMentoringPort {
    List<Mentoring> getMentoringList();

    Mentoring getMentoring(int mentoringId);
}
