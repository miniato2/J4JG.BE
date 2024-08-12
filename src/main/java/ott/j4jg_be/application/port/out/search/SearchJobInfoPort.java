package ott.j4jg_be.application.port.out.search;

import ott.j4jg_be.domain.collection.JobInfo;

import java.util.List;

public interface SearchJobInfoPort {

    List<JobInfo> searchJobInfo(String keyword, int categoryCode, int skillTag, int page);

    JobInfo jobInfoDetail(int jobInfoId);
}
