package ott.j4jg_be.adapter.in.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.application.port.in.NewsUsecase;
import ott.j4jg_be.domain.NewsArticle;

import java.util.List;

@RestController
public class NewsController {

    private final NewsUsecase newsUsecase;

    @Autowired
    public NewsController(NewsUsecase newsUsecase) {
        this.newsUsecase = newsUsecase;
    }

    @GetMapping("/news")
    public String fetchNews(@RequestParam String companyName) {
        newsUsecase.fetchAndSaveNews(companyName);
        return "News fetched and saved successfully";
    }
}
