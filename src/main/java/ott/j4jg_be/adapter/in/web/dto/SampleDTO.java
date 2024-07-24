package ott.j4jg_be.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.SampleEntity;

@Getter
@Setter
@Builder
public class SampleDTO {
    private String name;

    public static SampleDTO convertToDTO(SampleEntity sampleEntity) {
        return SampleDTO.builder()
                .name(sampleEntity.getName())
                .build();
    }
}
