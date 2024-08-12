package ott.j4jg_be.adapter.out.persistence.adapter.jpa.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringApplicationEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.mentoring.MentoringApplicationEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring.MentoringApplicationRepository;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringApplicationPort;
import ott.j4jg_be.domain.mentoring.MentoringApplication;

@Component
@RequiredArgsConstructor
public class GetMentoringApplicationAdapter implements GetMentoringApplicationPort {

    private final MentoringApplicationRepository mentoringApplicationRepository;
    private final MentoringApplicationEntityMapper mapper;


    @Override
    public Page<MentoringApplication> getApplicationList(int page) {

        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createAt"));

        Page<MentoringApplicationEntity> entities = mentoringApplicationRepository.findByStatus(false, pageable);
        return entities.map(mapper::mapToDomain);
    }
}
