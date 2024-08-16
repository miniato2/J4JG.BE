package ott.j4jg_be.application.port.out.scrap;

import ott.j4jg_be.domain.scrap.Scrap;

public interface UpdateScrapPort {
    void cancelScrap(int scrapId);

    void updateScrap(Scrap scrap, boolean b);
}
