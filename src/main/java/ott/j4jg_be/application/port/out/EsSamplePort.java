package ott.j4jg_be.application.port.out;

import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;

import java.util.List;

public interface EsSamplePort {
    EsSampleDTO saveEntity(EsSampleDTO entity);

    List<EsSampleDTO> findByField1(String field1);
}
