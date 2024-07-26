package ott.j4jg_be.adapter.in.web.Crawl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.application.port.in.CrawlingNewsUsecase;

@RestController
@RequiredArgsConstructor
public class CrawlingNewsController {

    private final CrawlingNewsUsecase crawlingNewsUsecase;

    @GetMapping("/news")
    public void crawlingNews(){
        crawlingNewsUsecase.CrawlingNews();
    }

}
