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
    public String mentoringApplication(String userId) {
        MentoringApplication application = getMentoringApplicationPort.getApplication(userId);

        String response = "";
        if(application == null){

            //신청 내역이 없을때
            mentoringApplicationPort.mentoringApplication(userId);
            response = "신청이 완료되었습니다.";

        }else if(!application.isStatus()){

            //신청시 신청내역 존재, status = false
            updateMentoringApplicationPort.updateStatus(application.getApplicationId());
            response = "신청이 완료되었습니다.";

        }else {

            //이미 매칭신청이력있음
            response = "이미 신청이 되었습니다.";

        }
        return response;
    }
}
