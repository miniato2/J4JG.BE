package ott.j4jg_be.adapter.out.persistence.mapper.mentoring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringEntity;
import ott.j4jg_be.domain.mentoring.Mentoring;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MentoringEntityMapper {
    private final ObjectMapper objectMapper;

    public MentoringEntity mapToEntity(Mentoring mentoring){

        try {
            return new MentoringEntity(
                    mentoring.getMentoringId(),
                    mentoring.getUserId(),
                    mentoring.getDescription(),
                    mentoring.getTitle(),
                    mentoring.getCreatedAt(),
                    mentoring.getUpdatedAt(),
                    mentoring.getStartDate(),
                    mentoring.getEndDate(),
                    mentoring.getLevel(),
                    mentoring.getPoint(),
                    objectMapper.writeValueAsString(mentoring.getSkillStack()),
                    objectMapper.writeValueAsString(mentoring.getWeek()),
                    mentoring.getType(),
                    mentoring.getMaxPerson(),
                    mentoring.getCurrentPerson(),
                    mentoring.isStatus()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Mentoring mapToDomain(MentoringEntity entity){
        try {
            return new Mentoring(
                    entity.getMentoringId(),
                    entity.getUserId(),
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
