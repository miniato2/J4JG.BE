package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.mentoring.MentoringApplicationUsecase;
import ott.j4jg_be.application.port.out.mentoring.MentoringApplicationPort;

@Service
@RequiredArgsConstructor
public class MentoringApplicationService implements MentoringApplicationUsecase{

    private final MentoringApplicationPort mentoringApplicationPort;

    @Override
    public void mentoringApplication(Long userId) {
        mentoringApplicationPort.mentoringApplication(userId);
    }
}
