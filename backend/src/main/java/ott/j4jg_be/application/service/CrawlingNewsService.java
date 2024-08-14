package ott.j4jg_be.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.out.crawler.dto.NewsDTO;
import ott.j4jg_be.application.port.in.CrawlingNewsUsecase;
import ott.j4jg_be.application.port.out.CrawlingNewsPort;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrawlingNewsService implements CrawlingNewsUsecase {

    private final CrawlingNewsPort crawlingNewsPort;
    private final ObjectMapper objectMapper;

    @Override
    public void CrawlingNews() {
        List<NewsDTO> newsList = crawlingNewsPort.crawlingNews();

        newsList.forEach(news -> {
            try {
                log.info(objectMapper.writeValueAsString(news));
            } catch (JsonProcessingException e) {
                log.warn("error");
            }
        });
    }
}
