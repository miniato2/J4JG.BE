package ott.j4jg_be.adapter.out.persistence.mapper.mentoring;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringApplicationEntity;
import ott.j4jg_be.domain.mentoring.MentoringApplication;

@Component
public class MentoringApplicationEntityMapper {

    public MentoringApplication mapToDomain(MentoringApplicationEntity entity){
        return new MentoringApplication(
                entity.getApplicationId(),
                entity.getUserId(),
//                "d",
//                "d",
                entity.getCreateAt(),
                entity.isStatus()
        );

    }
}
