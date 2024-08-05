package ott.j4jg_be.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.CrawlingNewsUsecase;
import ott.j4jg_be.application.port.out.GetCompanyPort;

@Service
@RequiredArgsConstructor
public class CrawlingNewsService implements CrawlingNewsUsecase {

    private final GetCompanyPort getCompanyPort;

    @Override
    public void CrawlingNews() {
        getCompanyPort.getCompany();
    }
}
