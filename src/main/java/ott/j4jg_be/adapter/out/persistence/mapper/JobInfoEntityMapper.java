package ott.j4jg_be.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;
import ott.j4jg_be.domain.JobInfo;

@Component
public class JobInfoEntityMapper {

    public JobInfoEntity mapToEntity(JobInfo jobInfo){
        return new JobInfoEntity(
                jobInfo.getId(),
                jobInfo.getCompanyId(),
                jobInfo.getCompanyName(),
                jobInfo.getCompanyImg(),
                jobInfo.getCompanyAvgRate(),
                jobInfo.getCompanyLvl(),
                jobInfo.getLocation(),
                jobInfo.getDistrict(),
                jobInfo.getPosition(),
                jobInfo.getCategoryCode(),
                jobInfo.getAttractionTags(),
                jobInfo.getSkillTag(),
                jobInfo.getAnnualFrom(),
                jobInfo.getAnnualTo(),
                jobInfo.isNewbie()
        );

    }
}
