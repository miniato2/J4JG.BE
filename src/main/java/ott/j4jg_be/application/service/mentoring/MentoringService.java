package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.application.port.in.mentoring.CreateMentoringUsecase;
import ott.j4jg_be.application.port.out.mentoring.CreateMentoringPort;

@Service
@RequiredArgsConstructor
public class MentoringService implements CreateMentoringUsecase {

    private final MentoringMapper mapper;
    private final CreateMentoringPort createMentoringPort;

    @Override
    public void createMentoring(MentoringDTO mentoringDTO) {
        createMentoringPort.createMentoring(mapper.mapToDomain(mentoringDTO));
    }


}
