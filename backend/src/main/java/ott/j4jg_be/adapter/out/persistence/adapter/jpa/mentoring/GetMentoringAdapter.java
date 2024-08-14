package ott.j4jg_be.adapter.out.persistence.adapter.jpa.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Mentoring> getMentoringList(int page) {

        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<MentoringEntity> mentoringEntities = mentoringRepository.findAllByOrderByUpdatedAtDesc(pageable);

        return mentoringEntities.map(mapper::mapToDomain);
    }

    @Override
    public Mentoring getMentoring(int mentoringId) {

        return mentoringRepository.findById(mentoringId)
                .map(mapper::mapToDomain)
                .orElse(null);
    }

    @Override
    public Page<Mentoring> getMyMentoring(String userId, int page, String status) {

        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        Page<MentoringEntity> mentoringEntities = null;

        if(status.equals("all")){
            mentoringEntities = mentoringRepository.findByUserId(userId, pageable);
        }else{
            mentoringEntities = mentoringRepository.findByUserIdAndStatus(userId, Boolean.parseBoolean(status), pageable);
        }

        return mentoringEntities.map(mapper::mapToDomain);
    }
}
