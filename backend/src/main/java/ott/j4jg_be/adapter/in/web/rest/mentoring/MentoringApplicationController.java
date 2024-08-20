package ott.j4jg_be.adapter.in.web.rest.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringApplicationDTO;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringApplicationQuery;
import ott.j4jg_be.application.port.in.mentoring.MentoringApplicationUsecase;
import ott.j4jg_be.common.annotation.CurrentUser;
import ott.j4jg_be.domain.user.TokenInfo;
import ott.j4jg_be.domain.user.User;


@RestController
@RequiredArgsConstructor
public class MentoringApplicationController {

    private final MentoringApplicationUsecase mentoringApplicationUsecase;
    private final GetMentoringApplicationQuery mentoringApplicationQuery;

    //멘토링신청 -> 멘티가
    @PostMapping("/application")
    public void mentoringApplication(@CurrentUser TokenInfo tokenInfo){
        mentoringApplicationUsecase.mentoringApplication(tokenInfo.getUserId());
    }

    //신청 조회 -> 관리자가
    @GetMapping("/application")
    public ResponseEntity<Page<MentoringApplicationDTO>> getMentoringApplicationList(@RequestParam(defaultValue = "0") int page){

        return ResponseEntity.ok().body(mentoringApplicationQuery.getApplicationList(page));
    }

}
