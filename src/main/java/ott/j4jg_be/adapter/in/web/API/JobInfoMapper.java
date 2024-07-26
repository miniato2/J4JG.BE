package ott.j4jg_be.adapter.in.web.API;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.JobInfoDTO;

@Component
@RequiredArgsConstructor
public class JobInfoMapper {

    private final ObjectMapper objectMapper;

    public JobInfoDTO mapToDTO(JsonNode jobNode){

        JobInfoDTO jobDTO = new JobInfoDTO();

        jobDTO.setAction_index("jobinfo");
        jobDTO.setAction("insert");
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

        return jobDTO;

    }
}
