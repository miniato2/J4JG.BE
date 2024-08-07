package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringApplicationDTO;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringApplicationQuery;
import ott.j4jg_be.application.port.in.mentoring.MentoringApplicationUsecase;
import ott.j4jg_be.application.port.out.mentoring.MentoringApplicationPort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentoringApplicationService implements MentoringApplicationUsecase{

    private final MentoringApplicationPort mentoringApplicationPort;

    @Override
    public void mentoringApplication(Long userId) {
        mentoringApplicationPort.mentoringApplication(userId);
    }
}
