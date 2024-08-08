package ott.j4jg_be.application.service.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.application.port.in.scrap.CancelScrapUsecase;
import ott.j4jg_be.application.port.in.point.PointUseCase;
import ott.j4jg_be.application.port.in.scrap.ScrapUsecase;
import ott.j4jg_be.application.port.out.scrap.CreateScrapPort;
import ott.j4jg_be.application.port.out.scrap.GetScrapPort;
import ott.j4jg_be.application.port.out.scrap.updateScrapPort;
import ott.j4jg_be.domain.scrap.Scrap;

@Service
@RequiredArgsConstructor
public class ScrapService implements ScrapUsecase, CancelScrapUsecase {

    private final CreateScrapPort createScrapPort;
    private final GetScrapPort getScrapPort;
    private final updateScrapPort cancelScrapPort;
    private final PointUseCase pointUseCase;

    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    @Override
    public void scrapJobInfo(Long userId,
                             int jobInfoId) {

        Scrap scrap = getScrapPort.getScrapByUserAndJobInfo(userId, jobInfoId);

        if(scrap != null && !scrap.isStatus()){
            cancelScrapPort.updateScrap(scrap, true);

        } else if(scrap == null){
            createScrapPort.createScrap(new Scrap(userId, jobInfoId, true));
            pointUseCase.addPoints(userId, 50, "스크랩");
        }
    }

    @Transactional
    @Override
    public void cancelScrap(int scrapId) {

        cancelScrapPort.cancelScrap(scrapId);
    }
}
