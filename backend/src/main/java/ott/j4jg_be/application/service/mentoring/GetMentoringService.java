package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringDTO;
import ott.j4jg_be.adapter.in.web.mapper.Mentoring.MentoringMapper;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringQuery;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringPort;
import ott.j4jg_be.domain.mentoring.Mentoring;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMentoringService implements GetMentoringQuery {

    private final GetMentoringPort getMentoringPort;
    private final MentoringMapper mapper;

    @Override
    public Page<MentoringDTO> getMentoringList(int page) {

        Page<Mentoring> mentoringList = getMentoringPort.getMentoringList(page);

        return mentoringList.map(mapper::mapToDTO);
    }

    @Override
    public MentoringDTO getMentoring(int mentoringId) {

        return mapper.mapToDTO(getMentoringPort.getMentoring(mentoringId));
    }

    @Override
    public Page<MentoringDTO> getMyMentoring(String userId, int page, String status) {

        Page<Mentoring> myMentoringList = getMentoringPort.getMyMentoring(userId, page, status);

        return myMentoringList.map(mapper::mapToDTO);
    }
}
