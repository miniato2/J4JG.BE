package ott.j4jg_be.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.JobInfoDTO;
import ott.j4jg_be.application.port.in.ApiSaveTestUsecase;
import ott.j4jg_be.application.port.out.ApiSaveTestPort;
import ott.j4jg_be.domain.JobInfo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiSaveTestService implements ApiSaveTestUsecase {

    private final ApiSaveTestPort apiSaveTestPort;

    @Override
    public void apiSave(List<JobInfoDTO> jobinfoDTOList) {
        List<JobInfo> jobInfoList = new ArrayList<>();

        for(JobInfoDTO jobInfoDTO: jobinfoDTOList){
            JobInfo jobInfo = new JobInfo(
                    jobInfoDTO.getId(),
                    jobInfoDTO.getCompanyId(),
                    jobInfoDTO.getCompanyName(),
                    jobInfoDTO.getCompanyImg(),
                    jobInfoDTO.getCompanyAvgRate(),
                    jobInfoDTO.getCompanyLvl(),
                    jobInfoDTO.getLocation(),
                    jobInfoDTO.getDistrict(),
                    jobInfoDTO.getPosition(),
                    jobInfoDTO.getCategoryCode(),
                    jobInfoDTO.getAttractionTags(),
                    jobInfoDTO.getSkillTag(),
                    jobInfoDTO.getAnnualFrom(),
                    jobInfoDTO.getAnnualTo(),
                    jobInfoDTO.isNewbie()
            );
            jobInfoList.add(jobInfo);
        }

        apiSaveTestPort.saveJobinfo(jobInfoList);

    }
}
