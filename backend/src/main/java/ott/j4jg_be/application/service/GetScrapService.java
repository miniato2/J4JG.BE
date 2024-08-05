package ott.j4jg_be.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ott.j4jg_be.application.port.in.GetScrapQuery;
import ott.j4jg_be.application.port.out.GetScrapPort;
import ott.j4jg_be.domain.Scrap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetScrapService implements GetScrapQuery {

    private final GetScrapPort getScrapPort;


    @Override
    public List<Scrap> getScrapList(Long userId) {

        return getScrapPort.getScrapList(userId);
    }
}
