package ott.j4jg_be.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.SampleEntity;
import ott.j4jg_be.application.port.in.SampleUseCase;
import ott.j4jg_be.application.port.out.SamplePort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SampleService implements SampleUseCase {

    private final SamplePort samplePort;

    public SampleService(SamplePort samplePort) {
        this.samplePort = samplePort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleDTO> getSamples() {
        return samplePort.findSamples().stream()
                .map(SampleDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Throwable.class)
    public SampleEntity saveSample(SampleDTO sampleDTO) {
        return samplePort.saveSample(sampleDTO);
    }
}
