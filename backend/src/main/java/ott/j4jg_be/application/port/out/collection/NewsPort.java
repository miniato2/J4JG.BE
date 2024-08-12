package ott.j4jg_be.application.port.out.collection;

import ott.j4jg_be.domain.collection.NewsArticle;

import java.util.List;

public interface NewsPort {
    void saveAll(List<NewsArticle> articles);
}