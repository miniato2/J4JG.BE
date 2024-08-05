package ott.j4jg_be.adapter.in.web.API;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.application.port.in.SaveJobInfoUsecase;


@RestController
@RequiredArgsConstructor
@Slf4j
public class SaveJobInfoController {

    private final SaveJobInfoUsecase saveJobInfoUsecase;

    @GetMapping("/api")
    public void saveJobInfo(){
        saveJobInfoUsecase.saveJobInfo();
    }

}
