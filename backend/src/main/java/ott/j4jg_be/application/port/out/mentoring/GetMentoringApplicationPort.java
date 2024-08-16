package ott.j4jg_be.application.port.out.mentoring;

import org.springframework.data.domain.Page;
import ott.j4jg_be.domain.mentoring.MentoringApplication;

public interface GetMentoringApplicationPort {

    Page<MentoringApplication> getApplicationList(int page);

    MentoringApplication getApplication(String userId);

}
