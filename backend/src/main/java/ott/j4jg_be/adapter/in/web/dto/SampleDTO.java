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

    // 기본 생성자
    public SampleDTO() {
    }

    // 모든 필드를 포함한 생성자
    public SampleDTO(String name) {
        this.name = name;
    }

    public static SampleDTO convertToDTO(SampleEntity sampleEntity) {
        return SampleDTO.builder()
                .name(sampleEntity.getName())
                .build();
    }
}
