package ott.j4jg_be.adapter.in.web.Crawl;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CompanyNameExtractor {
    private static final String LOG_FILE_PATH = "C:\\Project\\Final-Project\\J4JG_Backend\\logstash\\logdata\\logfile-2024-07-26.log";

    public Set<String> extractCompanyNames() {
        Set<String> companyNames = new HashSet<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOG_FILE_PATH));
            Pattern pattern = Pattern.compile("\"companyName\":\"([^\"]+)\"");

            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    companyNames.add(matcher.group(1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return companyNames;
    }
}
