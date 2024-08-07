package ott.j4jg_be.application.port.out.collection;

import ott.j4jg_be.adapter.out.crawler.dto.NewsDTO;

import java.util.List;

public interface CrawlingNewsPort {
    List<NewsDTO> crawlingNews(List<String> companyNames);
}
