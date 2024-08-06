package ott.j4jg_be.adapter.out.api.adapter;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ott.j4jg_be.adapter.out.api.mapper.JobInfoMapper;
import ott.j4jg_be.adapter.out.api.dto.JobInfoDTO;
import ott.j4jg_be.application.port.out.collection.CallJobInfoAPIPort;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CallJobInfoAPIAdapter implements CallJobInfoAPIPort {

    private final RestTemplate restTemplate;
    private final JobInfoMapper jobInfoMapper;

    @Override
    public List<JobInfoDTO> callJobInfoAPI() {
        List<JobInfoDTO> jobInfoList = new ArrayList<>();
        int i = 1;
        while (true) {
//            System.out.println(i + "페이지");
            String url = "https://www.wanted.co.kr/api/chaos/navigation/v1/results?job_group_id=518&&country=kr&job_sort=job.recommend_order&years=-1&locations=all&limit=100&offset=" + i;
            JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);

            try {
                JsonNode dataNode = rootNode.path("data");
                if (dataNode.isArray() && !dataNode.isEmpty()) {
                    for (JsonNode jobNode : dataNode) {
                        JobInfoDTO jobInfoDTO = jobInfoMapper.mapToDTO(jobNode);
                        jobInfoList.add(jobInfoDTO);
                    }
                } else {
                    //페이지에서 더이상 아이템이 없을시
                    break;
                }
                i += 1;
            } catch (Exception e) {
                throw new RuntimeException("Error fetching", e);
            }
        }
        return jobInfoList;
    }
}
