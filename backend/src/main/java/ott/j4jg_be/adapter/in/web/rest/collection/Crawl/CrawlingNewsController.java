package ott.j4jg_be.adapter.in.web.rest.collection.Crawl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.adapter.out.crawler.adapter.CompanyNameExtractor;
import ott.j4jg_be.application.port.in.collection.CrawlingNewsUsecase;

@RestController
@RequiredArgsConstructor
public class CrawlingNewsController {

//    private static final Logger logger = Logger.getLogger(CrawlingNewsController.class.getName());

    private final CompanyNameExtractor companyNameExtractor;
    private final CrawlingNewsUsecase crawlingNewsUsecase;


    @GetMapping("/api/news")
    public void crawl(){
        crawlingNewsUsecase.CrawlingNews();
    }

//    @GetMapping("/api/news")
//    public void crawl() {
//        Set<String> companyNames = companyNameExtractor.extractCompanyNames();
//
//        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//
//        WebDriver driver = new ChromeDriver(options);
//
//        try (FileWriter fileWriter = new FileWriter("C:\\Project\\Final-Project\\J4JG_Backend\\logstash\\logdata\\crawling_news.log", true)) {
//            for (String companyName : companyNames) {
//                String url = "https://search.naver.com/search.naver?where=news&query=" + companyName;
//                driver.get(url);
//
//                try {
//                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.news_tit")));
//
//                    List<WebElement> titles = driver.findElements(By.cssSelector("a.news_tit"));
//                    List<WebElement> urls = driver.findElements(By.cssSelector("a.news_tit"));
//
//                    for (int i = 0; i < titles.size(); i++) {
//                        String title = titles.get(i).getText();
//                        String articleUrl = urls.get(i).getAttribute("href");
//                        logger.info("companyName: " + companyName + ", news: " + title + ", url: " + articleUrl);
//                        String logEntry = String.format("{\"action_index\":\"news\",\"action\":\"insert\",\"companyName\":\"%s\",\"title\":\"%s\",\"url\":\"%s\"}", companyName, title, articleUrl);
//                        fileWriter.write(logEntry + "\n");
//                    }
//                } catch (Exception e) {
//                    logger.warning("Failed to fetch news for company: " + companyName + ", error: " + e.getMessage());
//                }
//            }
//        } catch (IOException e) {
//            logger.severe("Failed to write to log file: " + e.getMessage());
//        } finally {
//            driver.quit();
//        }
//    }
}