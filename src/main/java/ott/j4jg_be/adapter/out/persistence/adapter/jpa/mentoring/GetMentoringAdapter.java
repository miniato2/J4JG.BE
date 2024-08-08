package ott.j4jg_be.adapter.out.persistence.adapter.jpa.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.mentoring.MentoringEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring.MentoringRepository;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringPort;
import ott.j4jg_be.domain.mentoring.Mentoring;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetMentoringAdapter implements GetMentoringPort {

    private final MentoringRepository mentoringRepository;
    private final MentoringEntityMapper mapper;

    @Override
    public List<Mentoring> getMentoringList() {
        List<MentoringEntity> mentoringEntities = mentoringRepository.findAllByOrderByUpdatedAtDesc();
        return mentoringEntities.stream().map(mapper::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public Mentoring getMentoring(int mentoringId) {

        MentoringEntity entity = mentoringRepository.findById(mentoringId).orElse(null);
        if(entity != null){
            return mapper.mapToDomain(entity);
        }
        return null;
    }
}
