package ott.j4jg_be.adapter.out.persistence.mapper.mentoring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringEntity;
import ott.j4jg_be.domain.mentoring.Mentoring;

@Component
@RequiredArgsConstructor
public class MentoringEntityMapper {
    private final ObjectMapper objectMapper;

    public MentoringEntity mapToEntity(Mentoring mentoring){

        try {
            return MentoringEntity.builder()
                    .mentoringId(mentoring.getMentoringId())
                    .userId(mentoring.getUserId())
                    .description(mentoring.getDescription())
                    .title(mentoring.getTitle())
                    .createdAt(mentoring.getCreatedAt())
                    .updatedAt(mentoring.getUpdatedAt())
                    .startDate(mentoring.getStartDate())
                    .endDate(mentoring.getEndDate())
                    .level(mentoring.getLevel())
                    .point(mentoring.getPoint())
                    .skillStack(objectMapper.writeValueAsString(mentoring.getSkillStack()))
                    .week(objectMapper.writeValueAsString(mentoring.getWeek()))
                    .type(mentoring.getType())
                    .maxPerson(mentoring.getMaxPerson())
                    .currentPerson(mentoring.getCurrentPerson())
                    .status(mentoring.isStatus())
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Mentoring mapToDomain(MentoringEntity entity){
        try {
            return new Mentoring(
                    entity.getMentoringId(),
                    entity.getUser().getUserId(),
                    entity.getUser().getUserAddInfo().getUserNickname(),
                    entity.getDescription(),
                    entity.getTitle(),
                    entity.getCreatedAt(),
                    entity.getUpdatedAt(),
                    entity.getStartDate(),
                    entity.getEndDate(),
                    entity.getLevel(),
                    entity.getPoint(),
                    objectMapper.readValue(entity.getSkillStack(), String[].class),
                    objectMapper.readValue(entity.getWeek(), String[].class),
                    entity.getType(),
                    entity.getMaxPerson(),
                    entity.getCurrentPerson(),
                    entity.isStatus()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
