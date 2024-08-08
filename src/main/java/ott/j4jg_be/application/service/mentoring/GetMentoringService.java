package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
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
    public List<MentoringDTO> getMentoringList() {
        List<Mentoring> mentoringList = getMentoringPort.getMentoringList();
        return mentoringList.stream().map(mapper::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public MentoringDTO getMentoring(int mentoringId) {

        return mapper.mapToDTO(getMentoringPort.getMentoring(mentoringId));
    }
}
