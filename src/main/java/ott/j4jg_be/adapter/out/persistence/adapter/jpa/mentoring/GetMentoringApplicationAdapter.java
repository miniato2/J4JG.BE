package ott.j4jg_be.adapter.out.persistence.adapter.jpa.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringApplicationEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.mentoring.MentoringApplicationEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring.MentoringApplicationRepository;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringApplicationPort;
import ott.j4jg_be.domain.mentoring.MentoringApplication;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetMentoringApplicationAdapter implements GetMentoringApplicationPort {

    private final MentoringApplicationRepository mentoringApplicationRepository;
    private final MentoringApplicationEntityMapper mapper;


    @Override
    public List<MentoringApplication> getApplicationList() {

        List<MentoringApplicationEntity> entities = mentoringApplicationRepository.findByStatus(false);
        return entities.stream().map(mapper::mapToDomain).collect(Collectors.toList());
    }
}
