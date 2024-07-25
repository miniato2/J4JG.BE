package ott.j4jg_be.adapter.in.web.API;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ott.j4jg_be.adapter.in.web.dto.JobInfoDTO;
import ott.j4jg_be.application.port.in.ApiSaveTestUsecase;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestAPIController {

    private final RestTemplate restTemplate;
    private final JobInfoMapper jsonToDTOMapper;

    private final ApiSaveTestUsecase apiSaveTestUsecase;

    private String[] jobIds = {
            "10110", "873", "872", "669", "660", "900", "899", "1634",
            "674", "655", "895", "665", "677", "678", "658", "1026",
            "676", "877", "1024", "671", "1025", "672", "876", "1027",
            "10111", "10231", "893", "661", "896", "939", "10230",
            "898", "10112", "795", "1022", "894", "793"
    };


    @GetMapping("/api")
    public void callExternalApi() {

        List<JobInfoDTO> jobinfoDTOList = new ArrayList<>();

        for(String jobId: jobIds) {
            for (int i = 0; i < 1; i++) {
                String url = "https://www.wanted.co.kr/api/chaos/navigation/v1/results?job_group_id=518&job_ids=" + jobId + "&country=kr&job_sort=job.recommend_order&years=-1&locations=all&limit=2&offset=" + i;
                JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);
                log.info(rootNode.toString());
                try {
                    JsonNode dataNode = rootNode.path("data");
                    if (dataNode.isArray()) {
                        for (JsonNode jobNode : dataNode) {
                            jobinfoDTOList.add(jsonToDTOMapper.mapToDTO(jobNode));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to map JSON to DTO", e);
                }

            }
        }
        log.info(jobinfoDTOList.toString());

        apiSaveTestUsecase.apiSave(jobinfoDTOList);

        log.info("ok");
    }

}
