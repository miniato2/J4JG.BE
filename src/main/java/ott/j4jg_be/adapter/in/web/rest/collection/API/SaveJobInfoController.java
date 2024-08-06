package ott.j4jg_be.adapter.in.web.rest.collection.API;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.application.port.in.collection.SaveJobInfoUsecase;


@RestController
@RequiredArgsConstructor
@Slf4j
public class SaveJobInfoController {

    private final SaveJobInfoUsecase saveJobInfoUsecase;

    @GetMapping("/api")
    public void saveJobInfo(){
        saveJobInfoUsecase.saveJobInfo();
    }

//    @GetMapping("/api")
//    public void callExternalApi() {
//        int i = 0;
//        while (true) {
//            String url = "https://www.wanted.co.kr/api/chaos/navigation/v1/results?job_group_id=518&&country=kr&job_sort=job.recommend_order&years=-1&locations=all&limit=1&offset=" + i;
//            JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);
//
//            try {
//                JsonNode dataNode = rootNode.path("data");
//                if (dataNode.isArray() && !dataNode.isEmpty()) {
//                    for (JsonNode jobNode : dataNode) {
//                        JobInfoDTO jobInfoDTO = jobInfoMapper.mapToDTO(jobNode);
//                        //로그
//                        log.info(objectMapper.writeValueAsString(jobInfoDTO));
//
//                    }
//                } else {
//                    //페이지에 더이상 결과가 없을때 break
//                    System.out.println("no item");
//                    break;
//                }
//                i += 1;
//            } catch (Exception e) {
//                throw new RuntimeException("");
//            }
//        }
//
//        System.out.println("ok");
//    }


}
