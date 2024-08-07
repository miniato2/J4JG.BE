package ott.j4jg_be.application.port.out.mentoring;

import ott.j4jg_be.domain.mentoring.MentoringApplication;

import java.util.List;

public interface GetMentoringApplicationPort {

    List<MentoringApplication> getApplicationList();
}
