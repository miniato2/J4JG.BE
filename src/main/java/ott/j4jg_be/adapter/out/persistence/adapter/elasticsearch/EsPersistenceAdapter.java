package ott.j4jg_be.adapter.out.persistence.adapter.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;
import ott.j4jg_be.adapter.out.persistence.repository.elasticsearch.EsSampleRepository;
import ott.j4jg_be.application.port.out.EsSamplePort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EsPersistenceAdapter implements EsSamplePort {

    private final EsSampleRepository esSampleRepository;

    @Override
    public EsSampleDTO saveEntity(EsSampleDTO entity) {
        SampleEntity sampleEntity = SampleEntity.builder()
                .id(entity.getId())
                .field1(entity.getField1())
                .field2(entity.getField2())
                .build();
        return EsSampleDTO.convertToDTO(esSampleRepository.save(sampleEntity));
    }

    @Override
    public List<EsSampleDTO> findByField1(String field1) {
        return esSampleRepository.findAllByField1(field1)
                .stream()
                .map(EsSampleDTO::convertToDTO)
                .toList();
    }
}
