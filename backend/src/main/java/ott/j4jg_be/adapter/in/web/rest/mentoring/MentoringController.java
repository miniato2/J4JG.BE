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
    @PostMapping("/mentoring")
    public void createMentoring(@ModelAttribute MentoringDTO mentoringDTO){
        createMentoringUsecase.createMentoring(mentoringDTO);
    }

    //멘토링 리스트 조회 -> 관리자
    @GetMapping("/mentoring/all")
    public ResponseEntity<Page<MentoringDTO>> getMentoringList(int page){
//        return getMentoringQuery.getMentoringList();
        return ResponseEntity.ok().body(getMentoringQuery.getMentoringList(page));
    }

    //멘토링 상세 조회 -> 관리자,
    @GetMapping("/mentoring/{mentoringId}")
    public ResponseEntity<MentoringDTO> getMentoring(@PathVariable int mentoringId){
//        return getMentoringQuery.getMentoring(mentoringId);
        return ResponseEntity.ok().body(getMentoringQuery.getMentoring(mentoringId));
    }


    //자신의 멘토링 조회
    @GetMapping("/mentoring")
    public ResponseEntity<Page<MentoringDTO>> getMyMentoring(@CurrentUser User user, int page, String status) {
        //status -> false, true, all

        return ResponseEntity.ok().body(getMentoringQuery.getMyMentoring(user.getId(), page, status));
    }

    //멘토링 방 삭제
    @PutMapping("/mentoring/{mentoringId}")
    public void disableMentoring(@PathVariable int mentoringId){

        updateMentoringUsecase.disableMentoring(mentoringId);
    }


}
