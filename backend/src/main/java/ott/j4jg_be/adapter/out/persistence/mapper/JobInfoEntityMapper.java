package ott.j4jg_be.adapter.out.persistence.mapper;

import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.in.web.dto.search.JobInfoResponse;
import ott.j4jg_be.adapter.out.persistence.entity.elasticsearch.JobInfoEntity;
import ott.j4jg_be.domain.collection.JobInfo;

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

    public JobInfo mapToDomain(JobInfoEntity jobInfoEntity){
        return new JobInfo(
                jobInfoEntity.getId(),
                jobInfoEntity.getCompanyId(),
                jobInfoEntity.getCompanyName(),
                jobInfoEntity.getCompanyImg(),
                jobInfoEntity.getCompanyAvgRate(),
                jobInfoEntity.getCompanyLvl(),
                jobInfoEntity.getLocation(),
                jobInfoEntity.getDistrict(),
                jobInfoEntity.getPosition(),
                jobInfoEntity.getCategoryCode(),
                jobInfoEntity.getAttractionTags(),
                jobInfoEntity.getSkillTag(),
                jobInfoEntity.getAnnualFrom(),
                jobInfoEntity.getAnnualTo(),
                jobInfoEntity.isNewbie()
        );
    }

    public JobInfoResponse mapToDTo(JobInfo jobInfo){
        return new JobInfoResponse(
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
