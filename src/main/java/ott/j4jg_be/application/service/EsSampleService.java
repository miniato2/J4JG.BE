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

    private final EsSamplePort esSamplePort;

    @Override
    public EsSampleDTO saveEntity(EsSampleDTO dto) {
        return esSamplePort.saveEntity(dto);
    }

    @Override
    public List<EsSampleDTO> findByCompanyName(String companyName) {
        return esSamplePort.findByCompanyName(companyName);
    }

}
