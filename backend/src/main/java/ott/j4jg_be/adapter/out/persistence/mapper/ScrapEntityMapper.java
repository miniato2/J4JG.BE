package ott.j4jg_be.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.ScrapEntity;
import ott.j4jg_be.domain.Scrap;

@Component
public class ScrapEntityMapper {

    public ScrapEntity mapToEntity(Scrap scrap){

        return new ScrapEntity(
                scrap.getUserId(),
                scrap.getJobInfoId(),
                scrap.isStatus()
        );
    }

    public Scrap mapToDomain(ScrapEntity scrapEntity){
        return new Scrap(
                scrapEntity.getScrapId(),
                scrapEntity.getUserId(),
                scrapEntity.getJobInfoId(),
                scrapEntity.getStatus()
        );
    }
}
