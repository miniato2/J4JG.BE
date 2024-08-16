package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.adapter.in.web.mapper.mentoring.MentoringMapper;
import ott.j4jg_be.application.port.in.mentoring.CreateMentoringUsecase;
import ott.j4jg_be.application.port.in.mentoring.UpdateMentoringUsecase;
import ott.j4jg_be.application.port.out.mentoring.CreateMentoringPort;
import ott.j4jg_be.application.port.out.mentoring.GetMatchingPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringPort;

@Service
@RequiredArgsConstructor
public class MentoringService implements CreateMentoringUsecase, UpdateMentoringUsecase {

    private final MentoringMapper mapper;
    private final CreateMentoringPort createMentoringPort;
    private final GetMatchingPort getMatchingPort;
    private final UpdateMentoringPort updateMentoringPort;

    @Override
    public void createMentoring(MentoringDTO mentoringDTO) {

        createMentoringPort.createMentoring(mapper.mapToDomain(mentoringDTO));
    }

    @Transactional
    @Override
    public void disableMentoring(int mentoringId) {

        if(!getMatchingPort.existsMatching(mentoringId)){
            //없으면 삭제
            updateMentoringPort.updateStatus(mentoringId);
        }
    }
}
