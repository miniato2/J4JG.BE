package ott.j4jg_be.adapter.out.persistence.entity.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "sample")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SampleEntity {

    @Id
    private String id;
    private String rewardTotal;
    private boolean isBookmark;
    private String companyId;
    private String companyName;
    private double avgRate;
    private String level;
    private String origin;
    private String thumb;
    private String country;
    private String location;
    private String district;
    private String position;
    private int parentId;
    private int categoryId;
    private List<Integer> attractionTags;
    private List<Integer> skillTags;
    private List<Integer> userOrientedTags;
    private int annualFrom;
    private int annualTo;
    private boolean isNewbie;
    private int rewardRecommender;
    private int rewardRecommendee;
    private String rewardRecommenderUnit;
    private String rewardRecommendeeUnit;
    private String formattedTotal;
    private String formattedRecommender;
    private String formattedRecommendee;
}