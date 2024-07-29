package ott.j4jg_be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ott.j4jg_be.adapter.in.web.Crawl.CompanyNameExtractor;
import ott.j4jg_be.adapter.in.web.Crawl.CrawlingNewsController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@SpringBootApplication
public class J4JGBeApplication implements CommandLineRunner {

    @Autowired
    private CompanyNameExtractor companyNameExtractor;

    @Autowired
    private CrawlingNewsController crawlingNewsController;

    public static void main(String[] args) {
        SpringApplication.run(J4JGBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String logFilePath = "C:\\Project\\Final-Project\\J4JG_Backend\\logstash\\logdata\\logfile-2024-07-26.log";  // 실제 로그 파일 경로를 입력합니다.
        String outputFilePath = "C:\\Project\\Final-Project\\J4JG_Backend\\logstash\\logdata\\news_log.log";  // 크롤링된 뉴스 로그를 저장할 파일 경로

        try {
            String logData = new String(Files.readAllBytes(Paths.get(logFilePath)));
            List<String> companyNames = companyNameExtractor.extractCompanyNames(logData);

            for (String companyName : companyNames) {
                try {
                    List<CrawlingNewsController.NewsArticle> newsArticles = crawlingNewsController.crawl(companyName);
                    for (CrawlingNewsController.NewsArticle article : newsArticles) {
                        String logEntry = String.format("companyName: %s, news: %s, url: %s%n", companyName, article.getTitle(), article.getUrl());
                        Files.write(Paths.get(outputFilePath), logEntry.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                        // 로그
                        System.out.println(logEntry);
                    }
                } catch (Exception e) {
                    System.err.println("Error crawling news for company: " + companyName);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}