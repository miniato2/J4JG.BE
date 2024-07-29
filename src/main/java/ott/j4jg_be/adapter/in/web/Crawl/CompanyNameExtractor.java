package ott.j4jg_be.adapter.in.web.Crawl;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CompanyNameExtractor {

    private static final Pattern pattern = Pattern.compile("\"companyName\":\"(.*?)\"");

    public List<String> extractCompanyNames(String logData) {
        List<String> companyNames = new ArrayList<>();
        Matcher matcher = pattern.matcher(logData);
        while (matcher.find()) {
            companyNames.add(matcher.group(1));
        }
        return companyNames;
    }
}
