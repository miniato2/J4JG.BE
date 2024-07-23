package ott.j4jg_be.adapter.out.persistence.adapter;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.SampleEntity;
import ott.j4jg_be.adapter.out.persistence.repository.SampleRepository;
import ott.j4jg_be.application.port.out.SamplePort;

import java.util.List;

@Component
public class SamplePersistenceAdapter implements SamplePort {

    private final SampleRepository sampleRepository;

    public SamplePersistenceAdapter(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    @Override
    public List<SampleEntity> findSamples() {
        return sampleRepository.findAll();
    }

    @Override
    public SampleEntity saveSample(SampleDTO sampleDTO) {
        return sampleRepository.save(SampleEntity.builder().name(sampleDTO.getName()).build());
    }
}
