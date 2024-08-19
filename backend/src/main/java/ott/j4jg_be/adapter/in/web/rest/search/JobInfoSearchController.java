package ott.j4jg_be.adapter.in.web.rest.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.search.JobInfoResponse;
import ott.j4jg_be.application.port.in.search.SearchJobInfoQuery;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobInfoSearchController {

    private final SearchJobInfoQuery searchJobInfoQuery;

    /**
     * @api {get} /jobInfo/search 채용 정보 검색
     * @apiName SearchJobInfo
     * @apiGroup JobInfo
     *
     * @apiParam {String} keyword 검색할 키워드.
     * @apiParam {Number} [categoryCode=0] 카테고리 코드 (기본값: 0).
     * @apiParam {Number} [skillTag=0] 스킬 태그 코드 (기본값: 0).
     * @apiParam {Number} [page=0] 페이지 번호 (기본값: 0).
     *
     * @apiSuccess {Object[]} jobInfoList 검색된 채용 정보 목록.
     * @apiSuccess {Number} jobInfoList.id 채용 정보 ID.
     * @apiSuccess {Number} jobInfoList.companyId 회사 ID.
     * @apiSuccess {String} jobInfoList.companyName 회사 이름.
     * @apiSuccess {String} jobInfoList.companyImg 회사 이미지 URL.
     * @apiSuccess {String} jobInfoList.companyAvgRate 회사 평균 평가.
     * @apiSuccess {String} jobInfoList.companyLvl 회사 레벨.
     * @apiSuccess {String} jobInfoList.location 회사 위치 (지역).
     * @apiSuccess {String} jobInfoList.district 회사 위치 (구/군).
     * @apiSuccess {String} jobInfoList.position 채용 직무.
     * @apiSuccess {Number} jobInfoList.categoryCode 카테고리 코드.
     * @apiSuccess {Number[]} jobInfoList.attractionTags 인기 태그 목록.
     * @apiSuccess {Number[]} jobInfoList.skillTag 스킬 태그 목록.
     * @apiSuccess {Number} jobInfoList.annualFrom 최소 연차.
     * @apiSuccess {Number} jobInfoList.annualTo 최대 연차.
     * @apiSuccess {Boolean} jobInfoList.isNewbie 신입 가능 여부.
     */
    @GetMapping("/jobInfo/search")
    public ResponseEntity<List<JobInfoResponse>> searchJobInfo(@RequestParam(defaultValue = "") String keyword,
                                                               @RequestParam(defaultValue = "0") int categoryCode,
                                                               @RequestParam(defaultValue = "0") int skillTag,
                                                               @RequestParam(defaultValue = "0") int page){

        return ResponseEntity.ok().body(searchJobInfoQuery.searchJobInfo(keyword, categoryCode, skillTag, page));
    }

    /**
     * @api {get} /jobInfo/:jobInfoId 채용 정보 상세 조회
     * @apiName GetJobInfoDetail
     * @apiGroup JobInfo
     *
     * @apiParam {Number} jobInfoId 조회할 채용 정보의 고유 ID.
     *
     * @apiSuccess {Number} id 채용 정보 ID.
     * @apiSuccess {Number} companyId 회사 ID.
     * @apiSuccess {String} companyName 회사 이름.
     * @apiSuccess {String} companyImg 회사 이미지 URL.
     * @apiSuccess {String} companyAvgRate 회사 평균 평가.
     * @apiSuccess {String} companyLvl 회사 레벨.
     * @apiSuccess {String} location 회사 위치 (지역).
     * @apiSuccess {String} district 회사 위치 (구/군).
     * @apiSuccess {String} position 채용 직무.
     * @apiSuccess {Number} categoryCode 카테고리 코드.
     * @apiSuccess {Number[]} attractionTags 인기 태그 목록.
     * @apiSuccess {Number[]} skillTag 스킬 태그 목록.
     * @apiSuccess {Number} annualFrom 최소 연차.
     * @apiSuccess {Number} annualTo 최대 연차.
     * @apiSuccess {Boolean} isNewbie 신입 지원 가능 여부.
     */
    @GetMapping("/jobInfo/{jobInfoId}")
    public ResponseEntity<JobInfoResponse> jobInfoDetail(@PathVariable int jobInfoId){

        return ResponseEntity.ok().body(searchJobInfoQuery.jobInfoDetail(jobInfoId));
    }
}
