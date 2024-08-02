package ott.j4jg_be.adapter.out.persistence.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;

import java.util.Collection;
import java.util.List;

public interface EsSampleRepository extends ElasticsearchRepository<SampleEntity, String> {
    Collection<Object> findAllByCompanyName(String companyName);
}
