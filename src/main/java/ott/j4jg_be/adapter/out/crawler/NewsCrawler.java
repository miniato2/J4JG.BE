package ott.j4jg_be.adapter.out.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import ott.j4jg_be.application.port.out.NewsPort;
import ott.j4jg_be.domain.NewsArticle;

import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsCrawler {

    public List<NewsArticle> crawl(String companyName) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        String url = "https://search.naver.com/search.naver?where=news&query=" + companyName;
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list_news .bx")));

        List<WebElement> titles = driver.findElements(By.cssSelector(".news .type01 li .news_tit"));
        List<WebElement> contents = driver.findElements(By.cssSelector(".news .type01 li .dsc_wrap"));
        List<WebElement> dates = driver.findElements(By.cssSelector(".news .type01 li .info_group span.info"));

        List<NewsArticle> articles = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i).getText();
            String content = contents.get(i).getText();
            String articleUrl = titles.get(i).getAttribute("href");
            LocalDate date = LocalDate.parse(dates.get(i).getText());

            NewsArticle article = new NewsArticle(title, articleUrl, content, date);
            articles.add(article);
        }

        driver.quit();
        return articles;
    }
}