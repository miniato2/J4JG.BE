package ott.j4jg_be.application.port.out.test;

import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.SampleEntity;

import java.util.List;

public interface SamplePort {
    List<SampleEntity> findSamples();
    SampleEntity saveSample(SampleDTO sampleDTO);
}
