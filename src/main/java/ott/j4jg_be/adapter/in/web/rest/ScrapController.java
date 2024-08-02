package ott.j4jg_be.adapter.in.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.application.port.in.CancelScrapUsecase;
import ott.j4jg_be.application.port.in.ScrapUsecase;

@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapUsecase scrapUsecase;
    private final CancelScrapUsecase cancelScrapUsecase;

    @PostMapping("/scrap")
    public void scrapJobInfo(String userId,
                             int jobInfoId) {
        scrapUsecase.scrapJobInfo(userId, jobInfoId);
    }

    @PutMapping("/scrap")
    public void cancelScrap(int scrapId) {
        cancelScrapUsecase.cancelScrap(scrapId);
    }

}
