package ott.j4jg_be.adapter.out.api.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.JobInfoEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.elasticsearch.JobInfoEntityRepository;
import ott.j4jg_be.application.port.out.search.SearchJobInfoPort;
import ott.j4jg_be.domain.collection.JobInfo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchJobInfoAdapter implements SearchJobInfoPort {
    private final JobInfoEntityMapper mapper;

    private final ElasticsearchOperations elasticsearchOperations;
    private final JobInfoEntityRepository repository;

    @Override
    public List<JobInfo> searchJobInfo(String keyword, int categoryCode, int skillTag, int page) {

        NativeQueryBuilder queryBuilder = new NativeQueryBuilder();

        queryBuilder.withQuery(q -> q
                .bool(b -> {
                    if (keyword != null && !keyword.isEmpty()) {
                        b.must(m -> m
                                .match(mt -> mt
                                        .field("position")
                                        .query(keyword)
                                )
                        );
                    }
                    if (categoryCode > 0) {
                        b.must(m -> m
                                .term(t -> t
                                        .field("categoryCode")
                                        .value(categoryCode)
                                )
                        );
                    }
                    if (skillTag > 0) {
                        b.must(m -> m
                                .term(t -> t
                                        .field("skillTag")
                                        .value(skillTag)
                                )
                        );
                    }
                    return b;
                })
        );

        Pageable pageable = PageRequest.of(page, 10);

        queryBuilder.withPageable(pageable);
        NativeQuery query = queryBuilder.build();

        SearchHits<JobInfoEntity> searchHits = elasticsearchOperations.search(query, JobInfoEntity.class);

        return searchHits.getSearchHits()
                .stream()
                .map(hit -> mapper.mapToDomain(hit.getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public JobInfo jobInfoDetail(int jobInfoId) {
        return mapper.mapToDomain(Objects.requireNonNull(repository.findById(jobInfoId).orElse(null)));
    }
}
