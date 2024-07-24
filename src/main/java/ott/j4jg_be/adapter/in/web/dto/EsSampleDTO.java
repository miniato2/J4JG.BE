package ott.j4jg_be.adapter.in.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;

@Getter
@Setter
@Builder
public class EsSampleDTO {

    private String id;
    private String field1;
    private String field2;

    public static EsSampleDTO convertToDTO(SampleEntity sampleEntity) {
        return EsSampleDTO.builder()
                .id(sampleEntity.getId())
                .field1(sampleEntity.getField1())
                .field2(sampleEntity.getField2())
                .build();
    }
}
