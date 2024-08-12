package ott.j4jg_be.adapter.in.web.rest.test;

import lombok.RequiredArgsConstructor;
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
