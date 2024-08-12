package ott.j4jg_be.application.service.mentoring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MentoringApplicationDTO;
import ott.j4jg_be.adapter.in.web.mapper.Mentoring.MentoringApplicationMapper;
import ott.j4jg_be.application.port.in.mentoring.GetMentoringApplicationQuery;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringApplicationPort;
import ott.j4jg_be.domain.mentoring.MentoringApplication;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMentoringApplicationService implements GetMentoringApplicationQuery {

    private final GetMentoringApplicationPort getMentoringApplicationPort;
    private final MentoringApplicationMapper mapper;

    @Override
    public List<MentoringApplicationDTO> getApplicationList() {

        List<MentoringApplication> applicationList = getMentoringApplicationPort.getApplicationList();
        return applicationList.stream().map(mapper::mapToDTO).collect(Collectors.toList());
    }
}
