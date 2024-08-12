package ott.j4jg_be.application.port.in.search;

import ott.j4jg_be.adapter.in.web.dto.search.JobInfoResponse;
import ott.j4jg_be.adapter.out.api.dto.JobInfoDTO;

import java.util.List;

public interface SearchJobInfoQuery {

    List<JobInfoResponse> searchJobInfo(String keyword, int categoryCode, int skillTag, int page);
    JobInfoResponse jobInfoDetail(int jobInfoId);
}
