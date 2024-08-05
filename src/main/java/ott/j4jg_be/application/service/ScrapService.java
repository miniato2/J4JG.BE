package ott.j4jg_be.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.application.port.in.CancelScrapUsecase;
import ott.j4jg_be.application.port.in.PointUseCase;
import ott.j4jg_be.application.port.in.ScrapUsecase;
import ott.j4jg_be.application.port.out.CreateScrapPort;
import ott.j4jg_be.application.port.out.GetScrapPort;
import ott.j4jg_be.application.port.out.updateScrapPort;
import ott.j4jg_be.domain.Scrap;

@Service
@RequiredArgsConstructor
public class ScrapService implements ScrapUsecase, CancelScrapUsecase {

    private final CreateScrapPort createScrapPort;
    private final GetScrapPort getScrapPort;
    private final updateScrapPort cancelScrapPort;
    private final PointUseCase pointUseCase;

    @Transactional
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
