package ott.j4jg_be.adapter.out.crawler.adapter;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.crawler.dto.NewsDTO;
import ott.j4jg_be.application.port.out.collection.CrawlingNewsPort;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CrawlingNewsAdapter implements CrawlingNewsPort {

    private final CompanyNameExtractor companyNameExtractor;

    @Override
    public List<NewsDTO> crawlingNews() {
        Set<String> companyNames = companyNameExtractor.extractCompanyNames();

        System.setProperty("webdriver.gecko.driver", "/Users/seung/Downloads/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        WebDriver driver = new FirefoxDriver(options);

        List<NewsDTO> newsList = new ArrayList<>();

        try {
            for (String companyName : companyNames) {
                String company = companyName.replaceAll("\\(.*\\)", "").trim();

                String url = "https://search.naver.com/search.naver?where=news&query=" + company;
                driver.get(url);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

                int count = 0;
                while(count < 5){
                    try{
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.news_tit")));
                        List<WebElement> titles = driver.findElements(By.cssSelector("a.news_tit"));

                        for (WebElement webElement : titles) {
                            String title = webElement.getText();
                            String articleUrl = webElement.getAttribute("href");

                            newsList.add(new NewsDTO(
                                    "news",
                                    "insert",
                                    UUID.randomUUID().toString(),
                                    companyName,
                                    title,
                                    articleUrl
                            ));
                        }
                        break;
                    }catch (Exception e){
                        System.out.println(count);
                        count ++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return newsList;
    }
}
