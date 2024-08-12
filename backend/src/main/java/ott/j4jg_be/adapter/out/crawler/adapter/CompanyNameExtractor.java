package ott.j4jg_be.adapter.out.crawler.adapter;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CompanyNameExtractor {
    private static final String LOG_FILE_PATH = "logstash/logdata/logfile.log";
//    private static final String LOG_FILE_PATH = "logstash/logdata/jobinfo/jobinfo.log";
    private static final Pattern COMP_NAME_PATTERN = Pattern.compile("\"companyName\":\"([^\"]+)\"");

    public Set<String> extractCompanyNames() {
        Set<String> companyNames = new HashSet<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOG_FILE_PATH));

            for (String line : lines) {
                Matcher matcher = COMP_NAME_PATTERN.matcher(line);
                if (matcher.find()) {
                    companyNames.add(matcher.group(1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(companyNames);
        return companyNames;
    }
}
