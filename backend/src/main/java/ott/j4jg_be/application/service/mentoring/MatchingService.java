package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MatchingRequestDTO;
import ott.j4jg_be.application.port.in.mentoring.MatchingUsecase;
import ott.j4jg_be.application.port.in.point.PointUseCase;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringPort;
import ott.j4jg_be.application.port.out.mentoring.MatchingPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringApplicationPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringPort;
import ott.j4jg_be.application.port.out.notification.CreateNotificationPort;
import ott.j4jg_be.domain.mentoring.Matching;
import ott.j4jg_be.domain.mentoring.Mentoring;
import ott.j4jg_be.domain.notification.Notification;

@Service
@RequiredArgsConstructor
public class MatchingService implements MatchingUsecase {

    private final MatchingPort matchingPort;
    private final UpdateMentoringPort updateMentoringPort;
    private final GetMentoringPort getMentoringPort;
    private final UpdateMentoringApplicationPort updateMentoringApplicationPort;
    private final PointUseCase pointUseCase;
    private final CreateNotificationPort createNotificationPort;


    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void matching(MatchingRequestDTO matchingRequestDTO) {
        //동시성 문제 해결필요, 멘토링 방인원, 신청내역에 회원 상태

        //멘토링 방먼저 조회 -> 정원 확인위해
        Mentoring mentoring = getMentoringPort.getMentoring(matchingRequestDTO.getMentoringId());

        if(mentoring.isNotFull(mentoring.getMaxPerson(), mentoring.getCurrentPerson())){
            //매칭
            Matching matching = matchingPort.matching(matchingRequestDTO.getUserId(), matchingRequestDTO.getMentoringId());

            //멘토링 currentPerson 업데이트
            updateMentoringPort.updateCurrentPerson(matchingRequestDTO.getMentoringId());

            //application 상태 업데이트
            updateMentoringApplicationPort.updateStatus(matchingRequestDTO.getApplicationId());

            //포인트 차감
            pointUseCase.usePoints(matchingRequestDTO.getUserId(), 100, "멘토링 신청");

            //알림
            createNotificationPort.createNotification(new Notification("멘토링이 매칭 되었습니다.", matching.getMatchingId()));

        }
    }
}
