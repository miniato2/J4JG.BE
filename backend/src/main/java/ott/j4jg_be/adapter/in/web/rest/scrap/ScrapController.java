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
import ott.j4jg_be.domain.user.User;


@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapUsecase scrapUsecase;
    private final CancelScrapUsecase cancelScrapUsecase;
    private final GetScrapQuery getScrapQuery;

    /**
     * @api {post} /scrap 직업 정보 스크랩
     * @apiName ScrapJobInfo
     * @apiGroup Scrap
     *
     * @apiHeader {String} Authorization Bearer 토큰.
     *
     * @apiParam {Number} jobInfoId 스크랩할 직업 정보의 ID.
     */
    @PostMapping("/scrap")
    public void scrapJobInfo(@CurrentUser User user, int jobInfoId) {
        scrapUsecase.scrapJobInfo(user.getId(), jobInfoId);
    }

    /**
     * @api {put} /scrap 스크랩 취소
     * @apiName CancelScrap
     * @apiGroup Scrap
     *
     * @apiParam {Number} scrapId 취소할 스크랩의 ID.
     */
    @PutMapping("/scrap")
    public void cancelScrap(int scrapId) {

        cancelScrapUsecase.cancelScrap(scrapId);
    }


    /**
     * @api {get} /scrap 스크랩 목록 조회
     * @apiName GetScrapList
     * @apiGroup Scrap
     *
     * @apiHeader {String} Authorization Bearer 토큰.
     *
     * @apiParam {Number} page 페이지 번호 (기본값: 0).
     *
     * @apiSuccess {Object[]} scrapList 스크랩 목록.
     * @apiSuccess {Number} scrapList.scrapId 스크랩 ID.
     * @apiSuccess {String} scrapList.userId 사용자 ID.
     * @apiSuccess {Number} scrapList.jobInfoId 스크랩한 직업 정보의 ID.
     * @apiSuccess {Boolean} scrapList.status 스크랩 상태 (활성화/비활성화).
     */
    @GetMapping("/scrap")
    public ResponseEntity<Page<ScrapDTO>> getScrapList(@CurrentUser User user,
                                                       @RequestParam(defaultValue = "0") int page){

        return ResponseEntity.ok().body(getScrapQuery.getScrapList(user.getId(), page));
    }

}
