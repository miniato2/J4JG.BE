package ott.j4jg_be.application.service.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.search.JobInfoResponse;
import ott.j4jg_be.adapter.out.persistence.mapper.JobInfoEntityMapper;
import ott.j4jg_be.application.port.in.search.SearchJobInfoQuery;
import ott.j4jg_be.application.port.out.search.SearchJobInfoPort;
import ott.j4jg_be.domain.collection.JobInfo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchJobInfoService implements SearchJobInfoQuery {

    private final SearchJobInfoPort searchJobInfoPort;
    private final JobInfoEntityMapper mapper;
    @Override
    public List<JobInfoResponse> searchJobInfo(String keyword, int categoryCode, int skillTag, int page) {

        List<JobInfo> jobInfoList = searchJobInfoPort.searchJobInfo(keyword, categoryCode, skillTag, page);
        return jobInfoList.stream().map(mapper::mapToDTo).collect(Collectors.toList());
    }

    @Override
    public JobInfoResponse jobInfoDetail(int jobInfoId) {
        return mapper.mapToDTo(searchJobInfoPort.jobInfoDetail(jobInfoId));
    }
}
