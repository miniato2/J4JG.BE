package ott.j4jg_be.adapter.in.web.Crawl;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@RestController
@RequiredArgsConstructor
public class CrawlingNewsController {

    private static final Logger logger = Logger.getLogger(CrawlingNewsController.class.getName());

    private final CompanyNameExtractor companyNameExtractor;


    @GetMapping("/api/news")
    public void crawl() {
        try {
            // 로그를 파일에 저장하도록 설정
            FileHandler fileHandler = new FileHandler("C:\\Project\\Final-Project\\J4JG_Backend\\logstash\\logdata\\crawling_news.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.severe("Failed to set up logger file handler: " + e.getMessage());
        }

        Set<String> companyNames = companyNameExtractor.extractCompanyNames();

        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        for (String companyName : companyNames) {
            String url = "https://search.naver.com/search.naver?where=news&query=" + companyName;
            driver.get(url);

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.news_tit")));

                List<WebElement> titles = driver.findElements(By.cssSelector("a.news_tit"));
                List<WebElement> urls = driver.findElements(By.cssSelector("a.news_tit"));

                for (int i = 0; i < titles.size(); i++) {
                    String title = titles.get(i).getText();
                    String articleUrl = urls.get(i).getAttribute("href");
                    logger.info("companyName: " + companyName + ", news: " + title + ", url: " + articleUrl);
                }
            } catch (Exception e) {
                logger.warning("Failed to fetch news for company: " + companyName + ", error: " + e.getMessage());
            }
        }
        driver.quit();
    }
}