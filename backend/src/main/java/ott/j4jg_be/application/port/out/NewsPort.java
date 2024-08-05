package ott.j4jg_be.application.port.out;

import ott.j4jg_be.domain.NewsArticle;

import java.util.List;

public interface NewsPort {
    void saveAll(List<NewsArticle> articles);
}