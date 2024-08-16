package ott.j4jg_be.adapter.in.web.mapper.mentoring;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringApplicationDTO;
import ott.j4jg_be.domain.mentoring.MentoringApplication;

@Component
public class MentoringApplicationMapper {

    public MentoringApplicationDTO mapToDTO(MentoringApplication domain){

        return new MentoringApplicationDTO(
                domain.getApplicationId(),
                domain.getUserId(),
                domain.getUserName(),
                domain.getSurveyResponse(),
                domain.getCreatedAt()
        );
    }


}
