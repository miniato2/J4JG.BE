package ott.j4jg_be.application.port.in.scrap;

import ott.j4jg_be.domain.scrap.Scrap;

import java.util.List;

public interface GetScrapQuery {
    List<Scrap> getScrapList(Long userId);
}
