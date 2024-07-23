package ott.j4jg_be.application.service;

import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.SampleEntity;
import ott.j4jg_be.application.port.in.SampleUseCase;
import ott.j4jg_be.application.port.out.SamplePort;
import ott.j4jg_be.config.framework.db.ReadOnly;
import ott.j4jg_be.config.framework.db.WriteOnly;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SampleService implements SampleUseCase {

    private final SamplePort samplePort;

    public SampleService(SamplePort samplePort) {
        this.samplePort = samplePort;
    }

    @Override
    @ReadOnly
    public List<SampleDTO> getSamples() {
        return samplePort.findSamples().stream()
                .map(SampleDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @WriteOnly
    public SampleEntity saveSample(SampleDTO sampleDTO) {
        return samplePort.saveSample(sampleDTO);
    }
}
