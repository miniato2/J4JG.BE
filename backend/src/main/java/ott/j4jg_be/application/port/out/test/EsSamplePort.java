package ott.j4jg_be.application.port.out.test;

import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;

import java.util.List;

public interface EsSamplePort {
    EsSampleDTO saveEntity(EsSampleDTO entity);

    List<EsSampleDTO> findByCompanyName(String companyName);
}
