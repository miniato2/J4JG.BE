package ott.j4jg_be.application.port.in;

import ott.j4jg_be.adapter.in.web.dto.JobInfoDTO;

import java.util.List;

public interface ApiSaveTestUsecase {

    void apiSave(List<JobInfoDTO> jobinfoDTOList);
}
