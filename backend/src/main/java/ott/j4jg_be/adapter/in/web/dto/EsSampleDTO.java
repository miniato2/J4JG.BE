package ott.j4jg_be.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.SampleEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EsSampleDTO {
    private String id;
    private String rewardTotal;
    private boolean isBookmark;
    private CompanyDTO company;
    private TitleImgDTO titleImg;
    private AddressDTO address;
    private String position;
    private CategoryTagDTO categoryTag;
    private List<Integer> attractionTags;
    private List<Integer> skillTags;
    private List<Integer> userOrientedTags;
    private int annualFrom;
    private int annualTo;
    private boolean isNewbie;
    private RewardDTO reward;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompanyDTO {
        private String id;
        private String name;
        private ApplicationResponseStatsDTO applicationResponseStats;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApplicationResponseStatsDTO {
        private double avgRate;
        private String level;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TitleImgDTO {
        private String origin;
        private String thumb;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddressDTO {
        private String country;
        private String location;
        private String district;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CategoryTagDTO {
        private int parentId;
        private int id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RewardDTO {
        private String id;
        private String wdId;
        private String country;
        private String userCountry;
        private int rewardRecommender;
        private int rewardRecommendee;
        private String rewardRecommenderUnit;
        private String rewardRecommendeeUnit;
        private String formattedTotal;
        private String formattedRecommender;
        private String formattedRecommendee;
    }

    public SampleEntity toEntity() {
        SampleEntity.SampleEntityBuilder builder = SampleEntity.builder()
                .id(this.id)
                .rewardTotal(this.rewardTotal)
                .isBookmark(this.isBookmark)
                .companyId(this.company != null ? this.company.getId() : null)
                .companyName(this.company != null ? this.company.getName() : null)
                .avgRate(this.company != null && this.company.getApplicationResponseStats() != null ? this.company.getApplicationResponseStats().getAvgRate() : 0.0)
                .level(this.company != null && this.company.getApplicationResponseStats() != null ? this.company.getApplicationResponseStats().getLevel() : null)
                .origin(this.titleImg != null ? this.titleImg.getOrigin() : null)
                .thumb(this.titleImg != null ? this.titleImg.getThumb() : null)
                .country(this.address != null ? this.address.getCountry() : null)
                .location(this.address != null ? this.address.getLocation() : null)
                .district(this.address != null ? this.address.getDistrict() : null)
                .position(this.position)
                .parentId(this.categoryTag != null ? this.categoryTag.getParentId() : 0)
                .categoryId(this.categoryTag != null ? this.categoryTag.getId() : 0)
                .attractionTags(this.attractionTags)
                .skillTags(this.skillTags)
                .userOrientedTags(this.userOrientedTags)
                .annualFrom(this.annualFrom)
                .annualTo(this.annualTo)
                .isNewbie(this.isNewbie)
                .rewardRecommender(this.reward != null ? this.reward.getRewardRecommender() : 0)
                .rewardRecommendee(this.reward != null ? this.reward.getRewardRecommendee() : 0)
                .rewardRecommenderUnit(this.reward != null ? this.reward.getRewardRecommenderUnit() : null)
                .rewardRecommendeeUnit(this.reward != null ? this.reward.getRewardRecommendeeUnit() : null)
                .formattedTotal(this.reward != null ? this.reward.getFormattedTotal() : null)
                .formattedRecommender(this.reward != null ? this.reward.getFormattedRecommender() : null)
                .formattedRecommendee(this.reward != null ? this.reward.getFormattedRecommendee() : null);

        return builder.build();
    }

    public static EsSampleDTO fromEntity(SampleEntity entity) {
        return EsSampleDTO.builder()
                .id(entity.getId())
                .rewardTotal(entity.getRewardTotal())
                .isBookmark(entity.isBookmark())
                .company(CompanyDTO.builder()
                        .id(entity.getCompanyId())
                        .name(entity.getCompanyName())
                        .applicationResponseStats(ApplicationResponseStatsDTO.builder()
                                .avgRate(entity.getAvgRate())
                                .level(entity.getLevel())
                                .build())
                        .build())
                .titleImg(TitleImgDTO.builder()
                        .origin(entity.getOrigin())
                        .thumb(entity.getThumb())
                        .build())
                .address(AddressDTO.builder()
                        .country(entity.getCountry())
                        .location(entity.getLocation())
                        .district(entity.getDistrict())
                        .build())
                .position(entity.getPosition())
                .categoryTag(CategoryTagDTO.builder()
                        .parentId(entity.getParentId())
                        .id(entity.getCategoryId())
                        .build())
                .attractionTags(entity.getAttractionTags())
                .skillTags(entity.getSkillTags())
                .userOrientedTags(entity.getUserOrientedTags())
                .annualFrom(entity.getAnnualFrom())
                .annualTo(entity.getAnnualTo())
                .isNewbie(entity.isNewbie())
                .reward(RewardDTO.builder()
                        .rewardRecommender(entity.getRewardRecommender())
                        .rewardRecommendee(entity.getRewardRecommendee())
                        .rewardRecommenderUnit(entity.getRewardRecommenderUnit())
                        .rewardRecommendeeUnit(entity.getRewardRecommendeeUnit())
                        .formattedTotal(entity.getFormattedTotal())
                        .formattedRecommender(entity.getFormattedRecommender())
                        .formattedRecommendee(entity.getFormattedRecommendee())
                        .build())
                .build();
    }
}
