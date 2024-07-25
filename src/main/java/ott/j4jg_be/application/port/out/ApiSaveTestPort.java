package ott.j4jg_be.application.port.out;

import ott.j4jg_be.domain.JobInfo;

import java.util.List;

public interface ApiSaveTestPort {

    void saveJobinfo(List<JobInfo> jobinfoList);
}
