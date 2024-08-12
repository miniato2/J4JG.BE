package ott.j4jg_be.application.service.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapDTO;
import ott.j4jg_be.adapter.in.web.mapper.scrap.ScrapMapper;
import ott.j4jg_be.application.port.in.scrap.GetScrapQuery;
import ott.j4jg_be.application.port.out.scrap.GetScrapPort;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetScrapService implements GetScrapQuery {

    private final GetScrapPort getScrapPort;
    private final ScrapMapper mapper;

    @Override
    public List<ScrapDTO> getScrapList(Long userId) {

        return getScrapPort.getScrapList(userId).stream().map(mapper::mapToDTO).collect(Collectors.toList());
    }
}
