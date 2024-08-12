package ott.j4jg_be.adapter.out.persistence.adapter.jpa.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MentoringApplicationEntity;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring.MentoringApplicationRepository;
import ott.j4jg_be.application.port.out.mentoring.MentoringApplicationPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringApplicationPort;

@Component
@RequiredArgsConstructor

public class MentoringApplicationAdapter implements MentoringApplicationPort, UpdateMentoringApplicationPort {

    private final MentoringApplicationRepository mentoringApplicationRepository;

    @Override
    public void mentoringApplication(Long userId) {
        mentoringApplicationRepository.save(new MentoringApplicationEntity(userId));
    }

    @Override
    public void updateStatus(int applicationId) {
        MentoringApplicationEntity entity = mentoringApplicationRepository.findById(applicationId).get();
        entity.updateStatusToTrue();
    }
}
