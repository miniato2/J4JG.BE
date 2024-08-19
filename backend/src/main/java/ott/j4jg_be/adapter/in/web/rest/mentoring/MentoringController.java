package ott.j4jg_be.adapter.in.web.rest.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.application.port.in.mentoring.CreateMentoringUsecase;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringQuery;
import ott.j4jg_be.application.port.in.mentoring.UpdateMentoringUsecase;
import ott.j4jg_be.common.annotation.CurrentUser;
import ott.j4jg_be.domain.user.User;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class MentoringController {

    private final CreateMentoringUsecase createMentoringUsecase;
    private final GetMentoringQuery getMentoringQuery;
    private final UpdateMentoringUsecase updateMentoringUsecase;


    //role에 따른 접근제한필요

    //멘토링 생성 -> 멘토가
    /**
     * @api {post} /mentoring 멘토링 생성
     * @apiName createMentoring
     * @apiGroup Mentoring
     *
     * @apiParam {Number} mentoringId 멘토링 ID.
     * @apiParam {String} userId 사용자 ID.
     * @apiParam {String} userName 사용자 이름.
     * @apiParam {String} description 멘토링 설명.
     * @apiParam {String} title 멘토링 제목.
     * @apiParam {String} startDate 멘토링 시작일 (YYYY-MM-DD).
     * @apiParam {String} endDate 멘토링 종료일 (YYYY-MM-DD).
     * @apiParam {String} level 난이도 (초급, 중급, 상급).
     * @apiParam {Number} point 멘토링 포인트.
     * @apiParam {String[]} skillStack 필요한 기술 스택 (배열).
     * @apiParam {String[]} week 멘토링 요일 (배열).
     * @apiParam {String} type 멘토링 유형 (one, team, any).
     * @apiParam {Number} maxPerson 최대 참가 인원.
     * @apiParam {Number} currentPerson 현재 참가 인원.
     * @apiParam {Boolean} status 멘토링 상태 (진행 중/종료).
     * */
    @PostMapping("/mentoring")
    public void createMentoring(@ModelAttribute MentoringDTO mentoringDTO){
        createMentoringUsecase.createMentoring(mentoringDTO);
    }


    /**
     * @api {get} /mentoring/all 멘토링 리스트 조회
     * @apiName getMentoringList
     * @apiGroup Mentoring
     *
     * @apiParam {Number} page 페이지 번호 (기본값: 0).
     *
     * @apiSuccess {Object[]} mentoringList 멘토링 목록.
     * @apiSuccess {Number} mentoringList.mentoringId 멘토링 ID.
     * @apiSuccess {String} mentoringList.userId 사용자 ID.
     * @apiSuccess {String} mentoringList.userName 사용자 이름.
     * @apiSuccess {String} mentoringList.description 멘토링 설명.
     * @apiSuccess {String} mentoringList.title 멘토링 제목.
     * @apiSuccess {String} mentoringList.startDate 멘토링 시작일 (YYYY-MM-DD).
     * @apiSuccess {String} mentoringList.endDate 멘토링 종료일 (YYYY-MM-DD).
     * @apiSuccess {String} mentoringList.level 난이도 (초급, 중급, 상급).
     * @apiSuccess {Number} mentoringList.point 멘토링 포인트.
     * @apiSuccess {String[]} mentoringList.skillStack 필요한 기술 스택.
     * @apiSuccess {String[]} mentoringList.week 멘토링 요일.
     * @apiSuccess {String} mentoringList.type 멘토링 유형 (one, team, any).
     * @apiSuccess {Number} mentoringList.maxPerson 최대 참가 인원.
     * @apiSuccess {Number} mentoringList.currentPerson 현재 참가 인원.
     * @apiSuccess {Boolean} mentoringList.status 멘토링 상태 (진행 중/종료).
     * */
    //멘토링 리스트 조회 -> 관리자
    @GetMapping("/mentoring/all")
    public ResponseEntity<Page<MentoringDTO>> getMentoringList(@RequestParam(defaultValue = "0") int page){

        return ResponseEntity.ok().body(getMentoringQuery.getMentoringList(page));
    }

    //멘토링 상세 조회 -> 관리자,
    /**
     * @api {get} /mentoring/:mentoringId 특정 멘토링 조회
     * @apiName GetMentoring
     * @apiGroup Mentoring
     *
     * @apiParam {Number} mentoringId 멘토링의 고유 ID.
     *
     * @apiSuccess {Number} mentoringId 멘토링 ID.
     * @apiSuccess {String} userId 사용자 ID.
     * @apiSuccess {String} userName 사용자 이름.
     * @apiSuccess {String} description 멘토링 설명.
     * @apiSuccess {String} title 멘토링 제목.
     * @apiSuccess {String} startDate 멘토링 시작일 (YYYY-MM-DD).
     * @apiSuccess {String} endDate 멘토링 종료일 (YYYY-MM-DD).
     * @apiSuccess {String} level 난이도 (초급, 중급, 상급).
     * @apiSuccess {Number} point 멘토링 포인트.
     * @apiSuccess {String[]} skillStack 필요한 기술 스택.
     * @apiSuccess {String[]} week 멘토링 요일.
     * @apiSuccess {String} type 멘토링 유형 (one, team, any).
     * @apiSuccess {Number} maxPerson 최대 참가 인원.
     * @apiSuccess {Number} currentPerson 현재 참가 인원.
     * @apiSuccess {Boolean} status 멘토링 상태 (진행 중/종료).
     */
    @GetMapping("/mentoring/{mentoringId}")
    public ResponseEntity<MentoringDTO> getMentoring(@PathVariable int mentoringId){

        return ResponseEntity.ok().body(getMentoringQuery.getMentoring(mentoringId));
    }


    //자신의 멘토링 조회
    /**
     * @api {get} /mentoring 내 멘토링 목록 조회
     * @apiName GetMyMentoring
     * @apiGroup Mentoring
     *
     * @apiHeader {String} Authorization Bearer 토큰.
     *
     * @apiParam {Number} page 페이지 번호 (기본값: 0).
     * @apiParam {String="false","true","all"} status 멘토링 상태 필터링 (false: 진행 중, true: 완료됨, all: 모든 상태).
     *
     * @apiSuccess {Object[]} mentoringList 멘토링 목록.
     * @apiSuccess {Number} mentoringList.mentoringId 멘토링 ID.
     * @apiSuccess {String} mentoringList.userId 사용자 ID.
     * @apiSuccess {String} mentoringList.userName 사용자 이름.
     * @apiSuccess {String} mentoringList.description 멘토링 설명.
     * @apiSuccess {String} mentoringList.title 멘토링 제목.
     * @apiSuccess {String} mentoringList.startDate 멘토링 시작일 (YYYY-MM-DD).
     * @apiSuccess {String} mentoringList.endDate 멘토링 종료일 (YYYY-MM-DD).
     * @apiSuccess {String} mentoringList.level 난이도 (초급, 중급, 상급).
     * @apiSuccess {Number} mentoringList.point 멘토링 포인트.
     * @apiSuccess {String[]} mentoringList.skillStack 필요한 기술 스택.
     * @apiSuccess {String[]} mentoringList.week 멘토링 요일.
     * @apiSuccess {String} mentoringList.type 멘토링 유형 (one, team, any).
     * @apiSuccess {Number} mentoringList.maxPerson 최대 참가 인원.
     * @apiSuccess {Number} mentoringList.currentPerson 현재 참가 인원.
     * @apiSuccess {Boolean} mentoringList.status 멘토링 상태 (진행 중/종료).
     */
    @GetMapping("/mentoring")
    public ResponseEntity<Page<MentoringDTO>> getMyMentoring(@CurrentUser User user,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "all") String status) {
        //status -> false, true, all

        return ResponseEntity.ok().body(getMentoringQuery.getMyMentoring(user.getId(), page, status));
    }

    //멘토링 방 삭제
    /**
     * @api {put} /mentoring/:mentoringId 멘토링 비활성화
     * @apiName DisableMentoring
     * @apiGroup Mentoring
     *
     * @apiParam {Number} mentoringId 멘토링의 고유 ID.
     */
    @PutMapping("/mentoring/{mentoringId}")
    public void disableMentoring(@PathVariable int mentoringId){

        updateMentoringUsecase.disableMentoring(mentoringId);
    }


}
