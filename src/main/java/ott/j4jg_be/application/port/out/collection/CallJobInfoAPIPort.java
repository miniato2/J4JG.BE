package ott.j4jg_be.application.port.out.collection;

import ott.j4jg_be.adapter.out.api.dto.JobInfoDTO;

import java.util.List;

public interface CallJobInfoAPIPort {
    List<JobInfoDTO> callJobInfoAPI();
}
