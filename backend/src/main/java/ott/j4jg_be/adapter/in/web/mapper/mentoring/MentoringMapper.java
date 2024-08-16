package ott.j4jg_be.adapter.in.web.mapper.mentoring;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.domain.mentoring.Mentoring;

@Component
public class MentoringMapper {

    public Mentoring mapToDomain(MentoringDTO mentoringDTO){
        return new Mentoring(
                mentoringDTO.getMentoringId(),
                mentoringDTO.getUserId(),
                mentoringDTO.getUserName(),
                mentoringDTO.getDescription(),
                mentoringDTO.getTitle(),
                mentoringDTO.getCreatedAt(),
                mentoringDTO.getUpdatedAt(),
                mentoringDTO.getStartDate(),
                mentoringDTO.getEndDate(),
                mentoringDTO.getLevel(),
                mentoringDTO.getPoint(),
                mentoringDTO.getSkillStack(),
                mentoringDTO.getWeek(),
                mentoringDTO.getType(),
                mentoringDTO.getMaxPerson(),
                mentoringDTO.getCurrentPerson(),
                mentoringDTO.isStatus()
        );
    }

    public MentoringDTO mapToDTO(Mentoring mentoring){
        return new MentoringDTO(
                mentoring.getMentoringId(),
                mentoring.getUserId(),
                mentoring.getUserName(),
                mentoring.getDescription(),
                mentoring.getTitle(),
                mentoring.getCreatedAt(),
                mentoring.getUpdatedAt(),
                mentoring.getStartDate(),
                mentoring.getEndDate(),
                mentoring.getLevel(),
                mentoring.getPoint(),
                mentoring.getSkillStack(),
                mentoring.getWeek(),
                mentoring.getType(),
                mentoring.getMaxPerson(),
                mentoring.getCurrentPerson(),
                mentoring.isStatus()
        );
    }
}
