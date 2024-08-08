package ott.j4jg_be.adapter.out.persistence.adapter.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;
import ott.j4jg_be.adapter.out.persistence.repository.elasticsearch.EsSampleRepository;
import ott.j4jg_be.application.port.out.test.EsSamplePort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EsPersistenceAdapter implements EsSamplePort {

    private final EsSampleRepository esSampleRepository;

    @Override
    public EsSampleDTO saveEntity(EsSampleDTO dto) {
        SampleEntity entity = dto.toEntity();
        SampleEntity savedEntity = esSampleRepository.save(entity);
        return EsSampleDTO.fromEntity(savedEntity);
    }

    @Override
    public List<EsSampleDTO> findByCompanyName(String companyName) {
        return esSampleRepository.findAllByCompanyName(companyName)
                .stream()
                .map((Object entity) -> EsSampleDTO.fromEntity((SampleEntity) entity))
                .toList();
    }
}