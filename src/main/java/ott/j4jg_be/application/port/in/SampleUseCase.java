package ott.j4jg_be.application.port.in;

import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.SampleEntity;
import java.util.List;

public interface SampleUseCase {
    List<SampleDTO> getSamples();
    SampleEntity saveSample(SampleDTO sampleDTO);
}
