package ott.j4jg_be.application.port.in.scrap;

import org.springframework.data.domain.Page;
import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapDTO;


public interface GetScrapQuery {
    Page<ScrapDTO> getScrapList(String userId, int page);
}
