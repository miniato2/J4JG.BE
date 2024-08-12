package ott.j4jg_be.application.port.in.scrap;

import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapRequestDTO;

public interface ScrapUsecase {
    void scrapJobInfo(ScrapRequestDTO scrapRequestDTO);
}
