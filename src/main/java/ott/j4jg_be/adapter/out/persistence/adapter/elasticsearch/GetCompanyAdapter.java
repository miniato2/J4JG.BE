package ott.j4jg_be.adapter.out.persistence.adapter.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;
import ott.j4jg_be.adapter.out.persistence.repository.elasticsearch.JobInfoEntityRepository;
import ott.j4jg_be.application.port.out.GetCompanyPort;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCompanyAdapter implements GetCompanyPort {
    private final JobInfoEntityRepository jobInfoEntityRepository;
    @Override
    public void getCompany() {
        List<JobInfoEntity> jobInfoEntityList = jobInfoEntityRepository.findCompany();
        for(JobInfoEntity jobInfoEntity : jobInfoEntityList){
            System.out.println(jobInfoEntity);
        }

    }
}
