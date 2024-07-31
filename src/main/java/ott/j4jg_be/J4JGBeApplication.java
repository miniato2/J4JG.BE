package ott.j4jg_be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import ott.j4jg_be.adapter.in.web.Crawl.CompanyNameExtractor;
import ott.j4jg_be.adapter.in.web.Crawl.CrawlingNewsController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class J4JGBeApplication {
    public static void main(String[] args) {
        SpringApplication.run(J4JGBeApplication.class, args);
    }
}