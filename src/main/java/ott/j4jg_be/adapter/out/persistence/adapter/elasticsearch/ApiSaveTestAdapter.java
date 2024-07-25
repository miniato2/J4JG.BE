package ott.j4jg_be.adapter.out.persistence.adapter.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.JobInfoEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.elasticsearch.JobInfoEntityRepository;
import ott.j4jg_be.application.port.out.ApiSaveTestPort;
import ott.j4jg_be.domain.JobInfo;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApiSaveTestAdapter implements ApiSaveTestPort {

    private final JobInfoEntityRepository jobInfoEntityRepository;
    private final JobInfoEntityMapper jobInfoEntityMapper;

    @Override
    public void saveJobinfo(List<JobInfo> jobinfoList) {

        List<JobInfoEntity> jobInfoEntityList = new ArrayList<>();

        for(JobInfo jobInfo: jobinfoList){

            JobInfoEntity jobInfoEntity = jobInfoEntityMapper.mapToEntity(jobInfo);

            jobInfoEntityList.add(jobInfoEntity);
        }

        jobInfoEntityRepository.saveAll(jobInfoEntityList);
    }
}
