package ott.j4jg_be.application.port.in.scrap;

import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapDTO;
import ott.j4jg_be.domain.scrap.Scrap;

import java.util.List;

public interface GetScrapQuery {
    List<ScrapDTO> getScrapList(Long userId);
}
