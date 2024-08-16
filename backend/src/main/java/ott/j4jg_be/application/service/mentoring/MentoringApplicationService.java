package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.mentoring.MentoringApplicationUsecase;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringApplicationPort;
import ott.j4jg_be.application.port.out.mentoring.MentoringApplicationPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringApplicationPort;
import ott.j4jg_be.domain.mentoring.MentoringApplication;


@Service
@RequiredArgsConstructor
public class MentoringApplicationService implements MentoringApplicationUsecase{

    private final MentoringApplicationPort mentoringApplicationPort;
    private final GetMentoringApplicationPort getMentoringApplicationPort;
    private final UpdateMentoringApplicationPort updateMentoringApplicationPort;

    @Override
    public void mentoringApplication(String userId) {
        MentoringApplication application = getMentoringApplicationPort.getApplication(userId);

        if(application == null){
            //신청 내역이 없을때
            mentoringApplicationPort.mentoringApplication(userId);

        }else if(!application.isStatus()){
            //신청시 신청내역에서 status 가 false 상태인게 있을때
            updateMentoringApplicationPort.updateStatus(application.getApplicationId());
        }
        //이미 매칭신청이력있음
    }
}
