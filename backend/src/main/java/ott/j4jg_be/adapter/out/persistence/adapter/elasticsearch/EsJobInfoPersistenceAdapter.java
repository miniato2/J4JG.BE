package ott.j4jg_be.adapter.out.persistence.adapter.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;
import ott.j4jg_be.adapter.out.persistence.repository.elasticsearch.JobInfoEntityRepository;
import ott.j4jg_be.application.port.out.collection.JobInfoPort;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EsJobInfoPersistenceAdapter implements JobInfoPort {

//    private final JobInfoEntityRepository jobInfoEntityRepository;

    @Override
    public List<String> getCompanyNames(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
//        Page<JobInfoEntity> jobInfoPage = jobInfoEntityRepository.findAll(pageable);
//        if (jobInfoPage.hasContent()) {
//            return jobInfoPage.stream()
//                    .map(JobInfoEntity::getCompanyName)
//                    .collect(Collectors.toList());
//        } else {
            return List.of();
//        }
    }
}
