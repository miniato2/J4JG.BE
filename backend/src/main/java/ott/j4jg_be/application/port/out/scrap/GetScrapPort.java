package ott.j4jg_be.application.port.out.scrap;

import ott.j4jg_be.domain.scrap.Scrap;

import java.util.List;

public interface GetScrapPort {

    Scrap getScrapByUserAndJobInfo(Long userId, int jobInfoId);

    List<Scrap> getScrapList(Long userId);

}
