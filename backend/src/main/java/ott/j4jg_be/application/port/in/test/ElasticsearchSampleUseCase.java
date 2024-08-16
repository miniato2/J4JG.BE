package ott.j4jg_be.application.port.in.test;

import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;

import java.util.List;

public interface ElasticsearchSampleUseCase {
    EsSampleDTO saveEntity(EsSampleDTO dto);
    List<EsSampleDTO> findByCompanyName(String companyName);
}
