package ott.j4jg_be.adapter.in.web.API;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.adapter.in.web.dto.JobinfoDTO;
import ott.j4jg_be.application.service.EsSampleService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class TestAPIController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final EsSampleService esSampleService;

    private String[] jobIds = {
            "10110", "873", "872", "669", "660", "900", "899", "1634",
            "674", "655", "895", "665", "677", "678", "658", "1026",
            "676", "877", "1024", "671", "1025", "672", "876", "1027",
            "10111", "10231", "893", "661", "896", "939", "10230",
            "898", "10112", "795", "1022", "894", "793"
    };

    @GetMapping("/api")
    public void callExternalApi() {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (String jobId : jobIds) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                int offset = 0;
                String nextUrl = getApiUrl(jobId, offset);
                while (nextUrl != null) {
                    nextUrl = fetchAndSaveJobData(nextUrl, jobId, offset);
                    offset += 10; // 페이지당 2개의 데이터를 가져오므로 offset을 2씩 증가시킴
                }
            });

            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private String getApiUrl(String jobId, int offset) {
        return UriComponentsBuilder.fromHttpUrl("https://www.wanted.co.kr/api/chaos/navigation/v1/results")
                .queryParam("job_group_id", 518)
                .queryParam("job_ids", jobId)
                .queryParam("country", "kr")
                .queryParam("job_sort", "job.recommend_order")
                .queryParam("years", -1)
                .queryParam("locations", "all")
                .queryParam("limit", 50)
                .queryParam("offset", offset)
                .toUriString();
    }

    private String fetchAndSaveJobData(String url, String jobId, int offset) {
        try {
            JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);
            System.out.println(rootNode);

            JsonNode dataNode = rootNode.path("data");
            if (dataNode.isArray()) {
                for (JsonNode jobNode : dataNode) {
                    EsSampleDTO esSampleDTO = objectMapper.treeToValue(jobNode, EsSampleDTO.class);
                    esSampleService.saveEntity(esSampleDTO);
                }
            }
            JsonNode linksNode = rootNode.path("links");
            if (linksNode.has("next")) {
                String nextPath = linksNode.get("next").asText();
                return UriComponentsBuilder.fromHttpUrl("https://www.wanted.co.kr")
                        .path(nextPath)
                        .toUriString();
            } else {
                // 다음 페이지 링크가 없을 경우, 다음 offset을 증가시켜 다음 페이지 요청
                return getApiUrl(jobId, offset + 2);
            }
        } catch (HttpClientErrorException.NotFound e) {
            System.err.println("Failed to fetch data: 404 Not Found for URL " + url);
        } catch (Exception e) {
            System.err.println("Failed to map JSON to DTO: " + e.getMessage());
        }
        return null;
    }
}