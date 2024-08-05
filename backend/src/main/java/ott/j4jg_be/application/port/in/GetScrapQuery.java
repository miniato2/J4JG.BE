package ott.j4jg_be.application.port.in;

import ott.j4jg_be.domain.Scrap;

import java.util.List;

public interface GetScrapQuery {
    List<Scrap> getScrapList(Long userId);
}
