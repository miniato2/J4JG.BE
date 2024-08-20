package ott.j4jg_be.adapter.in.web.rest.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapDTO;
import ott.j4jg_be.application.port.in.scrap.CancelScrapUsecase;
import ott.j4jg_be.application.port.in.scrap.GetScrapQuery;
import ott.j4jg_be.application.port.in.scrap.ScrapUsecase;
import ott.j4jg_be.common.annotation.CurrentUser;
import ott.j4jg_be.domain.user.TokenInfo;
import ott.j4jg_be.domain.user.User;


@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapUsecase scrapUsecase;
    private final CancelScrapUsecase cancelScrapUsecase;
    private final GetScrapQuery getScrapQuery;

    @PostMapping("/scrap")
    public void scrapJobInfo(@CurrentUser TokenInfo tokenInfo, int jobInfoId) {

        scrapUsecase.scrapJobInfo(tokenInfo.getUserId(), jobInfoId);
    }

    @PutMapping("/scrap")
    public void cancelScrap(int scrapId) {

        cancelScrapUsecase.cancelScrap(scrapId);
    }

    @GetMapping("/scrap")
    public ResponseEntity<Page<ScrapDTO>> getScrapList(@CurrentUser TokenInfo tokenInfo,
                                                       @RequestParam(defaultValue = "0") int page){

        return ResponseEntity.ok().body(getScrapQuery.getScrapList(tokenInfo.getUserId(), page));
    }

}
