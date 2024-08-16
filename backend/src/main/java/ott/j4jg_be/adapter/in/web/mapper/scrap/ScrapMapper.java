package ott.j4jg_be.adapter.in.web.mapper.scrap;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapDTO;
import ott.j4jg_be.domain.scrap.Scrap;

@Component
public class ScrapMapper {

    public ScrapDTO mapToDTO(Scrap scrap){
        return new ScrapDTO(
                scrap.getScrapId(),
                scrap.getUserId(),
                scrap.getJobInfoId(),
                scrap.isStatus()
        );
    }

}