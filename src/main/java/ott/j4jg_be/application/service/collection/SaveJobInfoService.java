package ott.j4jg_be.application.service.collection;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.out.api.dto.JobInfoDTO;
import ott.j4jg_be.application.port.in.collection.SaveJobInfoUsecase;
import ott.j4jg_be.application.port.out.collection.CallJobInfoAPIPort;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveJobInfoService implements SaveJobInfoUsecase {

    private final CallJobInfoAPIPort callJobInfoAPIPort;
    private final ObjectMapper objectMapper;

    @Override
    public void saveJobInfo() {
        List<JobInfoDTO> jobInfoList = callJobInfoAPIPort.callJobInfoAPI();

        jobInfoList.forEach(jobInfoDTO -> {
            try {
                log.info(objectMapper.writeValueAsString(jobInfoDTO));
            } catch (Exception e) {
                log.error("error");
            }
        });
    }
}
