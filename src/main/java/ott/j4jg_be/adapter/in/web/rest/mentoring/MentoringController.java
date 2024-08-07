package ott.j4jg_be.adapter.in.web.rest.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.application.port.in.mentoring.CreateMentoringUsecase;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringQuery;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MentoringController {
    private final CreateMentoringUsecase createMentoringUsecase;
    private final GetMentoringQuery getMentoringQuery;


    //role에 따른 접근제한필요

    @PostMapping("/mentoring")
    public void createMentoring(@ModelAttribute MentoringDTO mentoringDTO){
        createMentoringUsecase.createMentoring(mentoringDTO);
    }

    @GetMapping("/mentoring")
    public List<MentoringDTO> getMentoringList(){
        return getMentoringQuery.getMentoringList();
    }

    @GetMapping("/mentoring/{mentoringId}")
    public MentoringDTO getMentoring(@PathVariable int mentoringId){
        return getMentoringQuery.getMentoring(mentoringId);
    }
}
