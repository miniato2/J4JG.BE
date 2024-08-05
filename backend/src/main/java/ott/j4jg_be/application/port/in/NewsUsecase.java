package ott.j4jg_be.application.port.in;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.service.NewsServiceImpl;
import ott.j4jg_be.domain.NewsArticle;

import java.util.List;

public interface NewsUsecase {
    void fetchAndSaveNews(String companyName);
}
