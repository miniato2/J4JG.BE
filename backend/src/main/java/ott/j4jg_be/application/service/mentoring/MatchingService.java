package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MatchingRequestDTO;
import ott.j4jg_be.application.port.in.mentoring.MatchingUsecase;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringPort;
import ott.j4jg_be.application.port.out.mentoring.MatchingPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringApplicationPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringPort;
import ott.j4jg_be.domain.mentoring.Mentoring;

@Service
@RequiredArgsConstructor
public class MatchingService implements MatchingUsecase {

    private final MatchingPort matchingPort;
    private final UpdateMentoringPort updateMentoringPort;
    private final GetMentoringPort getMentoringPort;
    private final UpdateMentoringApplicationPort updateMentoringApplicationPort;

    @Transactional
    @Override
    public void matching(MatchingRequestDTO matchingRequestDTO) {

        System.out.println(matchingRequestDTO.toString());

        //멘토링 방먼저 조회 -> 정원 확인위해
        Mentoring mentoring = getMentoringPort.getMentoring(matchingRequestDTO.getMentoringId());

        if(mentoring.isNotFull(mentoring.getMaxPerson(), mentoring.getCurrentPerson())){
            matchingPort.matching(matchingRequestDTO.getUserId(), matchingRequestDTO.getMentoringId());

            //멘토링 currentPerson 업데이트
            updateMentoringPort.updateCurrentPerson(matchingRequestDTO.getMentoringId());

            //application 상태 업데이트
            updateMentoringApplicationPort.updateStatus(matchingRequestDTO.getApplicationId());
        }
    }
}
