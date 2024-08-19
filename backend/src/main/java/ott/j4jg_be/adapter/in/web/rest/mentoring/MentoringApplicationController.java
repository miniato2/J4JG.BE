package ott.j4jg_be.adapter.in.web.rest.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringApplicationDTO;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringApplicationQuery;
import ott.j4jg_be.application.port.in.mentoring.MentoringApplicationUsecase;
import ott.j4jg_be.common.annotation.CurrentUser;
import ott.j4jg_be.domain.user.User;


@RestController
@RequiredArgsConstructor
public class MentoringApplicationController {

    private final MentoringApplicationUsecase mentoringApplicationUsecase;
    private final GetMentoringApplicationQuery mentoringApplicationQuery;

    //멘토링신청 -> 멘티가
    /**
     * @api {post} /application 멘토링 신청
     * @apiName mentoringApplication
     * @apiGroup Mentoring Application
     *
     * @apiHeader {String} Authorization Bearer Token.
     *
     * @apiSuccess {String} response 신청 결과 메시지.
     */
    @PostMapping("/application")
    public ResponseEntity<String> mentoringApplication(@CurrentUser User user){

        String response = mentoringApplicationUsecase.mentoringApplication(user.getId());
        return ResponseEntity.ok().body(response);
    }


    /**
     * @api {get} /application 멘토링 신청 조회
     * @apiName getMentoringApplicationList
     * @apiGroup Mentoring Application
     *
     * @apiParam {Number} page 페이지 번호 (기본값: 0).
     *
     * @apiSuccess {Object[]} applications 멘토링 신청 목록.
     * @apiSuccess {Number} applications.applicationId 신청 ID.
     * @apiSuccess {String} applications.userId 사용자 ID.
     * @apiSuccess {String} applications.userName 사용자 이름.
     * @apiSuccess {String} applications.surveyResponse 설문 응답.
     * @apiSuccess {String} applications.createdAt 신청 생성 시간.
     * */
    //신청 조회 -> 관리자가
    @GetMapping("/application")
    public ResponseEntity<Page<MentoringApplicationDTO>> getMentoringApplicationList(@RequestParam(defaultValue = "0") int page){

        return ResponseEntity.ok().body(mentoringApplicationQuery.getApplicationList(page));
    }

}
