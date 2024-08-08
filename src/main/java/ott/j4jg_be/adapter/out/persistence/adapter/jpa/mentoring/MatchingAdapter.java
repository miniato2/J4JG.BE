package ott.j4jg_be.adapter.out.persistence.adapter.jpa.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.adapter.out.persistence.entity.jpa.mentoring.MatchingEntity;
import ott.j4jg_be.adapter.out.persistence.repository.jpa.mentoring.MatchingRepository;
import ott.j4jg_be.application.port.out.mentoring.MatchingPort;

@Component
@RequiredArgsConstructor
public class MatchingAdapter implements MatchingPort {

    private final MatchingRepository matchingRepository;

    @Override
    public void matching(Long userId, int mentoringId) {

        matchingRepository.save(new MatchingEntity(userId, mentoringId));
    }
}
