package ott.j4jg_be.application.port.out.scrap;

import org.springframework.data.domain.Page;
import ott.j4jg_be.domain.scrap.Scrap;

public interface GetScrapPort {

    Scrap getScrapByUserAndJobInfo(String userId, int jobInfoId);

    Page<Scrap> getScrapList(String userId, int page);

}
