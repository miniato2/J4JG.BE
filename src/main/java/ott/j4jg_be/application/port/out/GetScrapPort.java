package ott.j4jg_be.application.port.out;

import ott.j4jg_be.domain.Scrap;

public interface GetScrapPort {

    Scrap getScrapByUserAndJobInfo(Long userId, int jobInfoId);

}
