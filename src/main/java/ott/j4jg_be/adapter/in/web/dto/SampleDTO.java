package ott.j4jg_be.adapter.in.web.dto;

import lombok.Getter;
import lombok.Setter;
import ott.j4jg_be.adapter.out.persistence.entity.SampleEntity;

@Getter
@Setter

public class SampleDTO {
    private String name;

    public SampleDTO(String name) {
        this.name = name;
    }


    public static SampleDTO convertToDTO(SampleEntity sampleEntity) {
        return new SampleDTO(sampleEntity.getName());
    }
}
