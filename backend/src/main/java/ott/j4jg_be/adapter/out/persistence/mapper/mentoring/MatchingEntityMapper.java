package ott.j4jg_be.adapter.out.persistence.mapper.mentoring;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MatchingEntity;
import ott.j4jg_be.domain.mentoring.Matching;

@Component
public class MatchingEntityMapper {

    public Matching mapToDomain(MatchingEntity matchingEntity){

        return new Matching(
                matchingEntity.getMatchingId(),
                matchingEntity.getMentoringId(),
                matchingEntity.getUserId(),
                matchingEntity.getUser().getUserAddInfo().getUserNickname(),
                matchingEntity.getUser().getUserEmail(),
                matchingEntity.getUser().getUserPhoneNumber(),
                matchingEntity.getUser().getUserAddInfo().getSurveyResponse(),
                matchingEntity.getCreatedAt(),
                matchingEntity.isStatus()
        );

    }
}
