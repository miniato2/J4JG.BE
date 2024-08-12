package ott.j4jg_be.adapter.out.persistence.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;

public interface JobInfoEntityRepository extends ElasticsearchRepository<JobInfoEntity, Integer> {

}
