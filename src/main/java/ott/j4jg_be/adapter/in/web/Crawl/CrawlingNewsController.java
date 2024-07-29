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
import ott.j4jg_be.application.port.in.CrawlingNewsUsecase;
import ott.j4jg_be.domain.NewsArticle;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CrawlingNewsController {

    public List<NewsArticle> crawl(String companyName) {
        List<NewsArticle> newsArticles = new ArrayList<>();

        // WebDriver 설정
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe"); // 크롬 드라이버 경로 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 브라우저 창을 띄우지 않고 실행
        WebDriver driver = new ChromeDriver(options);

        try {
            String url = "https://search.naver.com/search.naver?where=news&query=" + companyName;
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.news_tit"))); // 변경된 선택자 사용

            List<WebElement> titles = driver.findElements(By.cssSelector("a.news_tit"));
            for (WebElement title : titles) {
                String newsTitle = title.getText();
                String newsUrl = title.getAttribute("href");
                newsArticles.add(new NewsArticle(newsTitle, newsUrl));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return newsArticles;
    }

    public static class NewsArticle {
        private String title;
        private String url;

        public NewsArticle(String title, String url) {
            this.title = title;
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }
    }
}