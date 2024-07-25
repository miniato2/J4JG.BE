package ott.j4jg_be.adapter.in.web.API;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ott.j4jg_be.adapter.in.web.dto.EsSampleDTO;
import ott.j4jg_be.adapter.in.web.dto.JobinfoDTO;
import ott.j4jg_be.application.service.EsSampleService;

import java.util.ArrayList;
import java.util.List;

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
        List<EsSampleDTO> esSampleDTOList = new ArrayList<>();

        for(String jobId: jobIds) {
            for (int i = 0; i < 1; i++) {
                String url = "https://www.wanted.co.kr/api/chaos/navigation/v1/results?job_group_id=518&job_ids=" + jobId + "&country=kr&job_sort=job.recommend_order&years=-1&locations=all&limit=2&offset=" + i;
                JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);
                System.out.println(rootNode);

                try {
                    JsonNode dataNode = rootNode.path("data");
                    if (dataNode.isArray()) {
                        for (JsonNode jobNode : dataNode) {
                            EsSampleDTO esSampleDTO = objectMapper.treeToValue(jobNode, EsSampleDTO.class);
                            esSampleDTOList.add(esSampleService.saveEntity(esSampleDTO));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to map JSON to DTO", e);
                }
            }
        }
        System.out.println(esSampleDTOList);
    }
}
