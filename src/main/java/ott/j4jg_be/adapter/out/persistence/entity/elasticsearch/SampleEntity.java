package ott.j4jg_be.adapter.out.persistence.entity.elasticsearch;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "sample")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SampleEntity {
    @Id
    private String id;
    private String field1;
    private String field2;

    // getters and setters
}