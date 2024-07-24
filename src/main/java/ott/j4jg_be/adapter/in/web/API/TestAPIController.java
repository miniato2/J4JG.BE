package ott.j4jg_be.adapter.in.web.API;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ott.j4jg_be.adapter.in.web.dto.JobinfoDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestAPIController {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private String[] jobIds = {
            "10110", "873", "872", "669", "660", "900", "899", "1634",
            "674", "655", "895", "665", "677", "678", "658", "1026",
            "676", "877", "1024", "671", "1025", "672", "876", "1027",
            "10111", "10231", "893", "661", "896", "939", "10230",
            "898", "10112", "795", "1022", "894", "793"
    };


    @GetMapping("/api")
    public void callExternalApi() {

        List<JobinfoDTO> jobinfoDTOList = new ArrayList<>();

        for(String jobId: jobIds) {
            for (int i = 0; i < 1; i++) {
                String url = "https://www.wanted.co.kr/api/chaos/navigation/v1/results?job_group_id=518&job_ids=" + jobId + "&country=kr&job_sort=job.recommend_order&years=-1&locations=all&limit=2&offset=" + i;
                JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);
                System.out.println(rootNode);

                try {
                    JsonNode dataNode = rootNode.path("data");
                    if (dataNode.isArray()) {
                        for (JsonNode jobNode : dataNode) {
                            JobinfoDTO jobDTO = new JobinfoDTO();
                            jobDTO.setId(jobNode.path("id").asInt());

                            JsonNode companyNode = jobNode.path("company");
                            jobDTO.setCompanyId(companyNode.path("id").asInt());
                            jobDTO.setCompanyName(companyNode.path("name").asText());
                            jobDTO.setCompanyImg(jobNode.path("title_img").path("thumb").asText());
                            jobDTO.setCompanyAvgRate(companyNode.path("application_response_stats").path("avg_rate").asText());
                            jobDTO.setCompanyLvl(companyNode.path("application_response_stats").path("level").asText());

                            JsonNode addressNode = jobNode.path("address");
                            jobDTO.setLocation(addressNode.path("location").asText());
                            jobDTO.setDistrict(addressNode.path("district").asText());

                            jobDTO.setPosition(jobNode.path("position").asText());
                            jobDTO.setCategoryCode(jobNode.path("category_tag").path("id").asInt());
                            jobDTO.setAttractionTags(objectMapper.convertValue(jobNode.path("attraction_tags"), int[].class));
                            jobDTO.setSkillTag(objectMapper.convertValue(jobNode.path("skill_tags"), int[].class));
                            jobDTO.setAnnualFrom(jobNode.path("annual_from").asInt());
                            jobDTO.setAnnualTo(jobNode.path("annual_to").asInt());
                            jobDTO.setNewbie(jobNode.path("is_newbie").asBoolean());

                            jobinfoDTOList.add(jobDTO);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to map JSON to DTO", e);
                }

            }
        }
        System.out.println(jobinfoDTOList);
    }
}
