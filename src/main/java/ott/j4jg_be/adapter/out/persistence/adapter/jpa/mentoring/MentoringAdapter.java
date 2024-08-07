package ott.j4jg_be.adapter.out.persistence.adapter.jpa.mentoring;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringEntity;
import ott.j4jg_be.adapter.out.persistence.mapper.mentoring.MentoringEntityMapper;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring.MentoringRepository;
import ott.j4jg_be.application.port.out.mentoring.CreateMentoringPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringPort;
import ott.j4jg_be.domain.mentoring.Mentoring;

@Component
@RequiredArgsConstructor
public class MentoringAdapter implements CreateMentoringPort, UpdateMentoringPort {

    private final MentoringRepository mentoringRepository;
    private final MentoringEntityMapper mapper;

    @Override
    public void createMentoring(Mentoring mentoring) {
        mentoringRepository.save(mapper.mapToEntity(mentoring));
    }

    @Override
    public void updateCurrentPerson(int mentoringId) {
        MentoringEntity mentoring = mentoringRepository.findById(mentoringId).get();
        mentoring.updateCurrentPerson();
    }

}
