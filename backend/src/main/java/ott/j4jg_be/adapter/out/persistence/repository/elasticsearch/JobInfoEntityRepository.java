package ott.j4jg_be.adapter.out.persistence.repository.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;

import java.util.List;

public interface JobInfoEntityRepository extends ElasticsearchRepository<JobInfoEntity, Integer> {

//    @Query("{\"source\": [\"companyName\"], \"query\": {\"match_all\": {}}}")
//    List<JobInfoEntity> findCompany();
}
