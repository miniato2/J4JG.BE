package ott.j4jg_be.adapter.in.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;
import ott.j4jg_be.application.port.in.ElasticsearchSampleUseCase;
import ott.j4jg_be.application.port.in.SampleUseCase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entities")
public class ElasticsearchSampleController {

    private final ElasticsearchSampleUseCase sampleUseCase;

    @PostMapping
    public EsSampleDTO createEntity(@RequestBody EsSampleDTO entity) {
        return sampleUseCase.saveEntity(entity);
    }

    @GetMapping("/search")
    public List<EsSampleDTO> searchByField1(@RequestParam String field1) {
        return sampleUseCase.findByField1(field1);
    }
}
