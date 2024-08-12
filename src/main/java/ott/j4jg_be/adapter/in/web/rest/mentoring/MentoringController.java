package ott.j4jg_be.adapter.in.web.rest.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.application.port.in.mentoring.CreateMentoringUsecase;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringQuery;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MentoringController {
    private final CreateMentoringUsecase createMentoringUsecase;
    private final GetMentoringQuery getMentoringQuery;


    //role에 따른 접근제한필요

    //멘토링 생성 -> 멘토가
    @PostMapping("/mentoring")
    public void createMentoring(@ModelAttribute MentoringDTO mentoringDTO){
        createMentoringUsecase.createMentoring(mentoringDTO);
    }

    //멘토링 리스트 조회 -> 관리자
    @GetMapping("/mentoring")
    public ResponseEntity<List<MentoringDTO>> getMentoringList(){
//        return getMentoringQuery.getMentoringList();
        return ResponseEntity.ok().body(getMentoringQuery.getMentoringList());
    }


    //멘토링 상세 조회 -> 관리자
    @GetMapping("/mentoring/{mentoringId}")
    public ResponseEntity<MentoringDTO> getMentoring(@PathVariable int mentoringId){
//        return getMentoringQuery.getMentoring(mentoringId);
        return ResponseEntity.ok().body(getMentoringQuery.getMentoring(mentoringId));
    }
}
