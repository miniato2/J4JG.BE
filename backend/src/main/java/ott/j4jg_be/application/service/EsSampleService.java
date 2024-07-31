package ott.j4jg_be.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;
import ott.j4jg_be.application.port.in.ElasticsearchSampleUseCase;
import ott.j4jg_be.application.port.out.EsSamplePort;
import ott.j4jg_be.application.port.out.SamplePort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EsSampleService implements ElasticsearchSampleUseCase {

    private final EsSamplePort samplePort;


    @Override
    public EsSampleDTO saveEntity(EsSampleDTO entity) {
        return samplePort.saveEntity(entity);
    }

    @Override
    public List<EsSampleDTO> findByField1(String field1) {
        return samplePort.findByField1(field1);
    }
}
