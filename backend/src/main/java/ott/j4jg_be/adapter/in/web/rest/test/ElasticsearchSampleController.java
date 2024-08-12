package ott.j4jg_be.adapter.in.web.rest.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.application.port.in.test.ElasticsearchSampleUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entities")
public class ElasticsearchSampleController {

    private final ElasticsearchSampleUseCase sampleUseCase;

    @PostMapping
    public EsSampleDTO createEntity(@RequestBody EsSampleDTO entity) {
        return sampleUseCase.saveEntity(entity);
    }

}
