package ott.j4jg_be.application.service.collection;

import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.out.crawler.NewsCrawler;
import ott.j4jg_be.application.port.in.collection.NewsUsecase;
import ott.j4jg_be.application.port.out.collection.NewsPort;
import ott.j4jg_be.domain.collection.NewsArticle;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsUsecase {

    private final NewsCrawler newsCrawler;
    private final NewsPort newsPort;

    public NewsServiceImpl(NewsCrawler newsCrawler, NewsPort newsPort) {
        this.newsCrawler = newsCrawler;
        this.newsPort = newsPort;
    }

    @Override
    public void fetchAndSaveNews(String companyName) {
        List<NewsArticle> articles = newsCrawler.crawl(companyName);
        newsPort.saveAll(articles);
    }
}
