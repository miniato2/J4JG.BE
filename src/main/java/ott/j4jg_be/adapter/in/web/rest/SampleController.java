package ott.j4jg_be.adapter.in.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.in.web.dto.SampleDTO;
import ott.j4jg_be.application.port.in.SampleUseCase;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.SampleEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleUseCase sampleUseCase;

    @GetMapping("/samples")
    public List<SampleDTO> getSamples() {
        return sampleUseCase.getSamples();
    }

    @PostMapping("/samples")
    public SampleEntity saveSample(@RequestBody SampleDTO sampleDTO) {
        System.out.println(sampleDTO.getName());
        return sampleUseCase.saveSample(sampleDTO);
    }
}
